package ar.rami.megatron

import scala.util.{Failure, Success, Try}


sealed trait Command

object Command {
  def apply(command: String, params: Map[String, String]) = {

    command match {
      case "Welcome" => Welcome(params("name"), params("apocalypse").toInt, params("round").toInt)
      case "React" => {
        React(params("generation").toInt, params("name"), params("time").toInt, View(params("view")),
          params("energy").toInt, !params.get("master").map(_.toBoolean).getOrElse(true))
      }
      case "Goodbye" => Goodbye()
    }
  }
}

case class Welcome(name: String, apocalypse: Int, round: Int) extends Command

case class React(generation: Int, name: String, time: Int, view: View, energy: Int, master: Boolean) extends Command

case class Goodbye() extends Command

case class View(representation: String) {
  val size = math.sqrt(representation.length).toInt
  val matrix = Array.tabulate(size, size) { (x, y) =>
    (Point(x, y), representation(x * y + y))
  }.flatten.toMap

  val centerPoint = Point(size / 2, size / 2)

  def findNearest(terrain: Char) = {
    val filteredTerrain = matrix.filter(_._2 == terrain)
    println(filteredTerrain)
    if (filteredTerrain.isEmpty) {
      None
    } else
      Some(filteredTerrain.keys.minBy(_.distance(centerPoint)))
  }
}

case class Point(x: Int, y: Int) {
  def -(p: Point) = Point(x - p.x, y - p.y)

  def distance(p: Point) = {
    val v = this - p
    math.sqrt(v.x ^ 2 + v.y ^ 2)

  }
}
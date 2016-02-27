package ar.rami.megatron


sealed trait Command

object Command {
  def apply(command: String, params: Map[String, String]) = {
    command match {
      case "Welcome" => Welcome(params("name"), params("apocalypse").toInt, params("round").toInt)
      case "React" => React(params("generation").toInt, params("name"), params("time").toInt, View(params("view")),
        params("energy").toInt, !params("master").isEmpty)
      case "Goodbye" => Goodbye()
    }
  }
}

case class Welcome(name: String, apocalypse: Int, round: Int) extends Command

case class React(generation: Int, name: String, time: Int, view: View, energy: Int, master: Boolean) extends Command

case class Goodbye() extends Command

case class View(representation: String) {
  val size = math.sqrt(representation.length).toInt
  val matrix = representation.toCharArray.grouped(size).toArray

  override val toString = matrix.toString


}
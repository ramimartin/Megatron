package ar.rami.megatron

import scala.util.Random

trait Decepticon {
  def respond(input: String): String
}

class Megatron extends Decepticon {
  def respond(input: String): String = {
    CommandParser(input) match {
      case Welcome(name, apocalypse, round) => Status("Megatron").toString
      case React(_, _, _, view, _, _) => {
        val nearest = view.findNearest('b')
        println(nearest)
        (nearest match {
          case Some(n) => Move(-n.x, -n.y).toString
          case None => Move(Random.nextInt(1), Random.nextInt(1))
        }).toString


      }
      case Goodbye() => Rest().toString
      case _ => Rest().toString
    }

  }
}


package ar.rami.megatron

import scala.util.Random

trait Decepticon {
  def respond(input: String): String
}

class Megatron extends Decepticon {
  def respond(input: String): String = {
    CommandParser(input)  match {
      case Welcome(name, apocalypse, round) => Status("Megatron").toString
      case React(_,_,_,view,_,_) => {
        Move(Random.nextInt(6) - 3, Random.nextInt(6) - 3).toString}
      case Goodbye() => Rest().toString
    }

  }
}


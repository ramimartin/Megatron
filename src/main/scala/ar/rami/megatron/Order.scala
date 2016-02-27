package ar.rami.megatron

sealed trait Order


case class CompositeOrder(orders: Set[Order]) extends Order {
  override val toString = orders.mkString("|")
}

case class Rest() extends Order {
  override val toString = ""
}

case class Status(val text: String) extends Order {
  override val toString = s"Status(text=$text)"
}

case class Move(val x: Int, y: Int) extends Order {
  override val toString = s"Move(direction=$x:$y)"
}


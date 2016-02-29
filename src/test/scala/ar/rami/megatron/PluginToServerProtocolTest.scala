package ar.rami.megatron

import org.specs2._

class PluginToServerProtocolTest extends Specification{
  def is = s2"""

 This is a specification to check the 'Hello world' string

 Status object with a text should
   its represent protocol status                                 $e1

                                                                 """

  def e1 = Status("Megatron").toString must be equalTo("Status(text=Megatron)")

}

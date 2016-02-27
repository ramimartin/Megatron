package ar.rami.megatron


object CommandParser {
  def apply(paramsInput: String): Command = {
    def splitParam(param: String) = {
      val splitted = param.split("=").toList
      splitted match {
        case List(key, value) => (key, value)
        case _ => throw new IllegalArgumentException
      }
    }


    paramsInput.replace(")", "").split('(').toList match {
      case List(opcode, body) =>
        val params = body.split(',').map(splitParam).toMap
        Command(opcode, params)

    }
  }
}

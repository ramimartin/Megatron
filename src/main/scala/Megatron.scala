import ar.rami.megatron.Megatron

class ControlFunctionFactory {
  def create = (input: String) => {
    new Megatron().respond(input)
  }

}


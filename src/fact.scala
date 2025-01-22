object FactObj:
  object Config:
    inline val logging = false

  object Logger:
    private var indent = -1

    inline def log[T](msg: String, indentMargin: => Int)(op: => T): T =
      if Config.logging then
        println(s"${"  " * indent}start $msg")
        indent += indentMargin
        val result = op
        indent -= indentMargin
        println(s"${"  " * indent}$msg = $result")
        result
      else op
  end Logger

  var indentSetting = 2

  def factorial(n: BigInt): BigInt =
    Logger.log(s"factorial($n)", indentSetting) {
      if n == 0 then 1
      else n * factorial(n - 1)
    }


  def main(args: Array[String]) =
    println(factorial(3))

end FactObj

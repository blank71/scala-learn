object InlineMatch:
  trait Nat
  case object Zero extends Nat
  case class Succ[N <: Nat](n: N) extends Nat

  transparent inline def toInt(n: Nat): Int =
    inline n match
      case Zero     => 0
      case Succ(n1) => toInt(n1) + 1

  def main(args: Array[String]) =
    inline val x = toInt(Succ(Succ(Zero)))
    val x2: Int = x
    val x3: 2 = x

    // error because x is literal constant type
    // val x4: 3 = x 

end InlineMatch

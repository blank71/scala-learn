import java.awt.Color
import scala.collection.SortedSet
object Col {
  def main(args: Array[String]) = {
    // Iterable("x", "y", "z")
    // Map("x" -> 24, "y" -> 25, "z" -> 26)
    // Set(Color.red, Color.green, Color.blue)
    // SortedSet("hello", "world")
    // Buffer(x,y,z)

    // scala> List(1, 2, 3) map (_ + 1)
    // val res0: List[Int] = List(2, 3, 4)
    List(1, 2, 3) map (_ + 1)

    // scala> Set(1, 2, 3) map (_ * 2)
    // val res2: Set[Int] = Set(2, 4, 6)
    Set(1, 2, 3) map (_ * 2)
    ()
  }
}

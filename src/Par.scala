//> using dep "org.scala-lang.modules::scala-parallel-collections:1.1.0"
import scala.collection.parallel.CollectionConverters._

object Par {
  def main(args: Array[String]) = {
    {
      var start = System.nanoTime()

      val a = (1 to 1000000).toArray
      a.fold(0)((x, y) => x + y)

      val elasp = System.nanoTime() - start
      println(s"Time: ${elasp / 1000000} ms")
    }
    {
      var start = System.nanoTime()

      val a = (1 to 1000000).toArray.par
      a.fold(0)((x, y) => x + y)

      val elasp = System.nanoTime() - start
      println(s"Time: ${elasp / 1000000} ms")
    }
  }
}

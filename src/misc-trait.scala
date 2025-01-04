import scala.reflect.ClassTag
object MiscTrait {
  def make_title(title: String): String = s"// *** $title ***"
  def section(title: String, x: () => Unit) = {
    println(make_title(title))
    x()
    println(make_title(title) + "\n")
  }
  def main(args: Array[String]) = {
    {
      section(
        "キャッシュを利用したマップアクセス",
        () => {
          def f(s: String): String = {
            println("...zzzZZZ")
            Thread.sleep(100)
            s.reverse
          }
          val cache = collection.mutable.Map[String, String]()
          def cachedF(s: String): String = cache.getOrElseUpdate(s, f(s))

          var start = System.nanoTime()
          println(cachedF("abc"))
          var elasp = System.nanoTime() - start
          println(s"Time: ${elasp / 1000000} ms")

          start = System.nanoTime()
          println(cachedF("abc"))
          elasp = System.nanoTime() - start
          println(s"Time: ${elasp / 1000000} ms")
        }
      )
    }
  }
}

object SetTrait {
  def make_title(title: String): String = s"// *** $title ***"
  def section(title: String, x: () => Unit) = {
    println(make_title(title))
    x()
    println(make_title(title) + "\n")
  }
  def main(args: Array[String]) = {
    // https://docs.scala-lang.org/overviews/collections-2.13/sets.html
    {
      section(
        "イミュータブル Set に要素を追加",
        () => {
          // val だと変更できない
          var s = collection.immutable.Set(1, 2, 3)
          println(s"var s = collection.immutable.Set(1, 2, 3) = ${s}")
          println(s"s += 4")
          println(s += 4)
          println(s"s -= 2")
          println(s -= 2)
          println(s"s = ${s}")
        }
      )
    }
    {
      section(
        "ミュータブル Set に要素を追加",
        () => {
          var s = collection.mutable.Set(1, 2, 3)
          println(s"val s = collection.mutable.Set(1, 2, 3) = ${s}")
          println(s"s += 4")
          println(s += 4)
          println(s"s -= 2")
          println(s -= 2)
          println(s"s = ${s}")
        }
      )
    }
  }
}

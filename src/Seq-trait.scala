import _root_.scala.collection.Searching.Found
import _root_.scala.collection.Searching.InsertionPoint
import _root_.javax.naming.directory.SearchResult
object SeqTrait {
  def make_title(title: String): String = s"// *** $title ***"
  def section(title: String, x: () => Unit) = {
    println(make_title(title))
    x()
    println(make_title(title) + "\n")
  }
  def main(args: Array[String]) = {
    // https://docs.scala-lang.org/overviews/collections-2.13/seqs.html
    {
      section(
        "インデックス参照、長さ",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = ${xs}")

          val xa = xs(2)
          println(s"val xa: Int = xs(2) = ${xa}")

          val xb = xs apply 2
          println(s"val xb: Int = xs apply 2 = ${xb}")

          val xc = xs.length
          println(s"val xc: Int =  xs.length = ${xc}")

          val xd = xs lengthCompare List(1, 2, 3)
          println(s"val xd: Int = xs lengthCompare List(1,2,3) = ${xd}")

          val xe = xs lengthCompare List(1, 2, 3, 4, 5)
          println(s"val xe: Int = xs lengthCompare List(1,2,3,4,5) = ${xe}")

          val xf = xs lengthCompare List(1, 2, 3, 4, 5, 6)
          println(s"val xf: Int = xs lengthCompare List(1,2,3,4,5,6) = ${xf}")

          val xg = xs.indices
          println(s"val xg: Range = xs.indices = ${xg}")

          val xss = List(1, 3, 4)
          println(s"val xss: List[Int] = ${xss}")

          val xh = xss.indices
          println(s"val xh: Range = xss.indices = ${xh}")
        }
      )
    }
    {
      section(
        "インデックスの検索",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = ${xs}")

          val xa = xs indexOf 3
          println(s"val xa: Int = xs indexOf 3 = ${xa}")

          val xb = xs indexOfSlice List(3, 4)
          println(s"val xb: Int = xs indexOfSlice List(3, 4) = ${xb}")

          val xc = xs indexWhere (x => x >= 3)
          println(s"val xc: Int = xs indexWhere (x => x >= 3) = ${xc}")
        }
      )
    }
    {
      section(
        "追加 ",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = ${xs}")

          val xa = -1 +: 0 +: xs
          println(s"val xa: List[Int] = -1 +: 0 +: xs = ${xa}")

          val xb = List(-1, 0) ++: xs
          println(s"val xb: List[Int] = List(-1, 0) ++: xs = ${xs}")

          val xc = xs :+ 6 :+ 7
          println(s"val xc: List[Int] = xs :+ 6 :+ 7 = ${xc}")

          val xd = xs :++ List(6, 7)
          println(s"val xd: List[Int] = xs :++ List(6, 7) = ${xd}")

          val xe = xs.padTo(8, 2)
          println(s"val xe: List[Int] = xs.padTo(8, 2) = ${xe}")
        }
      )
    }
    {
      section(
        "更新",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = ${xs}")

          val xa = xs.patch(2, List(11, 12, 13), 0)
          println(
            s"val xa: List[Int] = xs.patch(2, List(11, 12, 13), 0) = ${xa}"
          )

          val xb = xs.patch(2, List(11, 12, 13), 1)
          println(
            s"val xb: List[Int] = xs.patch(2, List(11, 12, 13), 1) = ${xb}"
          )

          val xc = xs.updated(2, 11)
          println(s"val xc: List[Int] = xs.updated(2, 11) = ${xc}")
        }
      )
    }
    {
      section(
        "ソート",
        () => {
          val xa = List(-4, -2, -1, 2, 5, 11).sorted
          println(
            s"val xa: List[Int] = List(-4, -2, -1, 2, 5, 11).sorted = ${xa}"
          )

          val xb = List(-4, -2, -1, 2, 5, 11) sortWith ((x, y) =>
            if (x * x == y * y) then x < y else x * x < y * y
          )
          println(
            s"val xb: List[Int] = " +
              s"List(-4, -2, -1, 2, 5, 11) " +
              s"sortWith ((x, y) => " +
              s"if (x * x == y * y) " +
              s"then x < y else x * x < y * y) = " +
              s"${xb}"
          )

          val xc = List(-4, 2, -1, -2, 5, 11) sortBy (x => x * x)
          println(
            s"val xc: List[Int] = " +
              s"List(-4, 2, -1, -2, 5, 11) sortBy (x => x * x) = " +
              s"${xc}"
          )
        }
      )
    }
    {
      section(
        "反転",
        () => {
          val xa = List(1, 2, 3).reverse
          println(s"val xa: List[Int] = List(1,2,3).reverse = ${xa}")
        }
      )
    }
    {
      section(
        "比較",
        () => {
          val a = List(1, 2, 3) sameElements List(1, 2, 3)
          println(
            s"val a: Boolean = List(1, 2, 3) sameElements List(1, 2, 3) = ${a}"
          )

          val b = List(1, 2, 3) sameElements List(1, 2, 4)
          println(
            s"val b: Boolean = List(1, 2, 3) sameElements List(1, 2, 4) = ${b}"
          )

          val c = (0 to 10).toList startsWith (0 to 3).toList
          println(
            s"val c: Boolean = (0 to 10).toList startsWith (0 to 3).toList = ${c}"
          )

          val d = (0 to 10).toList startsWith (0 to 11).toList
          println(
            s"val d: Boolean = (0 to 10).toList startsWith (0 to 11).toList = ${d}"
          )

          def found(
              r: scala.collection.Searching.SearchResult
          ): (Int, Boolean) = {
            r match {
              case Found(index) =>
                println(s"index: $index")
                (index, true)
              case InsertionPoint(index) =>
                println(s"Not found")
                (index, false)
            }
          }

          val e = found((0 to 10).toList search 3)
          println(
            s"val e: (Int, Boolean) = found ((0 to 10).toList search 3) = ${e}"
          )

          val f = found((0 to 10).toList search 11)
          println(
            s"val f: (Int, Boolean) = found ((0 to 10).toList search 11) = ${f}"
          )

          val g =
            (0 to 10).toList.corresponds((0 to 10).toList)((x, y) => x + y >= 0)
          println(
            s"val g = (0 to 10).toList.corresponds ((0 to 10).toList) ((x,y) => x + y >= 0) = $g"
          )

          val h =
            (0 to 10).toList.corresponds((0 to 11).toList)((x, y) => x + y >= 0)
          println(
            s"val h = (0 to 10).toList.corresponds ((0 to 11).toList) ((x,y) => x + y >= 0) = $h"
          )

          val i =
            (0 to 10).toList.corresponds((0 to 10).toList)((x, y) => x + y >= 1)
          println(
            s"val i = (0 to 10).toList.corresponds ((0 to 10).toList) ((x,y) => x + y >= 1) = $i"
          )
        }
      )
    }
    {
      section(
        "集合間演算",
        () => {
          val xa = (0 to 9).toList.intersect((2 to 5).toList)
          println(
            s"val xa = (0 to 9).toList.intersect((2 to 5).toList) = ${xa}"
          )

          val xb = (0 to 9).toList.diff((2 to 5).toList)
          println(s"val xb = (0 to 9).toList.diff((2 to 5).toList) = ${xb}")

          val xc = List(1, 1, 2, 2, 3).distinct
          println(s"val xc = List(1, 1, 2, 2, 3).distinct = ${xc}")

          val xd = List(-1, 1, 2, -2, 3) distinctBy (x => x * x)
          println(
            s"val xd = List(-1, 1, 2, -2, 3) distinctBy (x => x * x) = ${xd}"
          )
        }
      )
    }
  }
}

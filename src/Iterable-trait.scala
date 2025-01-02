object IterableTrait {
  def make_title(title: String): String = s"// *** $title ***"
  def section(title: String, x: () => Unit) = {
    println(make_title(title))
    x()
    println(make_title(title) + "\n")
  }
  def main(args: Array[String]) = {
    // https://docs.scala-lang.org/overviews/collections-2.13/trait-iterable.html
    {
      // イテレーション
      section(
        "イテレーション",
        () => {
          println("// List の定義")
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = ${xs}")

          println("// grouped")
          val git = xs grouped 3
          println("val git: Iterator[List[Int]] = xs grouped 3 = <iterator>")
          println(s"var g: List[Int] = git.next = ${git.next}")
          println(s"var g: List[Int] = git.next = ${git.next}")
          // println(s"var g: List[Int] = git.next = ${git.next}") // error

          println("// sliding")
          val sit = xs sliding 3
          println("val sit: Iterator[List[Int]] = xs sliding 3 = <iterator>")
          println(s"var s: List[Int] = sit.next = ${sit.next}")
          println(s"var s: List[Int] = sit.next = ${sit.next}")
          println(s"var s: List[Int] = sit.next = ${sit.next}")

          println("// foreach")
          println("xs foreach (x => println(s\"squared: $x -> ${x * x}\"))")
          xs foreach (x => println(s"squared: $x -> ${x * x}"))
        }
      )
    }
    {
      // concat: 追加
      // コレクションの後ろにコレクション、または、イテレータが生成(yield)するすべての要素を追加する
      section(
        "追加 concat",
        () => {
          {
            val xs = List(1, 2, 3, 4, 5)
            println(s"val xs: List[Int] = $xs")

            val ys = xs.concat(List(4, 5, 6))
            println(s"val ys: List[Int] = xs.concat(List(4, 5, 6)) = $ys")
          }
          {
            val xs = List(1, 2, 3, 4, 5)
            println(s"val xs: List[Int] = $xs")

            val ys = xs concat List(4, 5, 6)
            println(s"val ys: List[Int] = xs concat List(4, 5, 6) = $ys")
          }
          {
            val xs = List(1, 2, 3, 4, 5)
            println(s"val xs: List[Int] = $xs")

            val ys = xs ++ List(4, 5, 6)
            println(s"val ys: List[Int] = xs ++ List(4, 5, 6) = $ys")
          }
        }
      )
    }
    {
      // map: 写像
      // コレクションの要素に何らかの関数を適用して新いコレクションを作る
      section(
        "map",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          println("// simple map")
          println(s"xs map (x => x * x) = ${xs map (x => x * x)}")

          println("// compare map and flatMap")
          println(
            s"xs map (x => List(x, x * x)) = ${xs map (x => List(x, x * x))}"
          )

          println(
            s"xs flatMap (x => List(x, x * x)) = ${xs flatMap (x => List(x, x * x))}"
          )

          println(
            s"xs collect ({ case x if (x * x > 9) => x }) = ${xs collect ({
                case x if (x * x > 9) => x
              })}"
          )
        }
      )
    }
    // 変換
    // コレクションを受け取って新しいコレクションを返す
    {
      section(
        "変換",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          println(s"val xss: Array[Int] = ${xs.toArray})}")
        }
      )
    }
    // コピー
    {
      section(
        "コピー",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          val xss = new Array[Int](6)
          println(
            s"var xss: Array[Int] = Array(${xss
                .mkString("", ", ", "")})"
          )

          xs.copyToArray(xss)
          println(
            s"var xss: Array[Int] = xs.copyToArray(xss) = Array(${xss
                .mkString("", ", ", "")})"
          )

          val xsss = new Array[Int](6)
          xs.copyToArray(xsss, 2)
          println(
            s"var xsss: Array[Int] = xs.copyToArray(xsss, 2) = Array(${xsss
                .mkString("", ", ", "")})"
          )
        }
      )
    }
    // サイズ情報
    {
      section(
        "サイズ情報",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          val s = xs.size
          println(s"val s: Int = xs.size = ${s}")
        }
      )
    }
    // 要素取得
    {
      section(
        "要素取得",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          val h = xs.head
          println(s"val h: Int = xs.head = $h")

          val fa = xs find (x => x > 3)
          println(s"val fa: Option[Int] = xs find (x => x > 3) = $fa")

          val fb = xs find (x => x > 10)
          println(s"val fb: Option[Int] = xs find (x => x > 10) x = $fb")
        }
      )
    }
    // 部分コレクション取得
    {
      section(
        "部分コレクション取得",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          val t = xs.tail
          println(s"val t: List[Int] = xs.tail = $t")

          val xsa = xs take 2
          println(s"val xss: List[Int] = xs take 2 = ${xsa}")

          val xsb = xs drop 2
          println(s"val xss: List[Int] = xs drop 2 = ${xsb}")

          val xsc = xs filter (x => x > 3)
          println(s"val xsc: List[Int] = xs filter (x => x > 3) = ${xsc}")
        }
      )
    }
    // 部分コレクションへの分割
    {
      section(
        "部分コレクションへの分割",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          val xa = xs span (x => x < 3)
          println(
            s"val xa: (List[Int], List[Int]) = xs span (x => x < 3) = ${xa}"
          )

          val xb = xs span (x => x > 3)
          println(
            s"val xb: (List[Int], List[Int]) = xs span (x => x > 3) = ${xb}"
          )

          val xc = xs partition (x => x > 3)
          println(
            s"val xc: (List[Int], List[Int]) = xs partition (x => x > 3) = ${xc}"
          )
        }
      )
    }
    // 要素テスト
    {
      section(
        "要素テスト",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          val xa = xs forall (x => x > 0)
          println(s"val xa: Boolean = xs forall (x => x > 0) = ${xa}")

          val xb = xs forall (x => x > 1)
          println(s"val xb: Boolean = xs forall (x => x > 1) = ${xb}")

          val xc = xs exists (x => x > 3)
          println(s"val xc: Boolean = xs exists (x => x > 3) = ${xc}")

          val xd = xs exists (x => x > 6)
          println(s"val xd: Boolean = xs exists (x => x > 6) = ${xd}")

          val xe = xs count (x => x > 1)
          println(s"val xe: Int = xs count (x => x > 1) = ${xe}")
        }
      )
    }
    // 畳み込み
    {
      section(
        "畳み込み",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          val x = xs.fold(0)((x, y) => x + y)
          println(s"val x: Int = xs.fold(0)((x, y) => x + y) = ${x}")
        }
      )
    }
    // 特定の要素型を対象とする畳み込み
    {
      section(
        "特定の要素型を対象とする畳み込み",
        () => {
          val xs = List(1, 2, 3, 4, 5)
          println(s"val xs: List[Int] = $xs")

          val x = xs.fold(0)((x, y) => x + y)
          println(s"val x: Int = xs.fold(0)((x, y) => x + y) = ${x}")

          val xa = xs.product
          println(s"val xa: Int = xs.product = ${xa}")

          val xb = xs.fold(1)((x, y) => x * y)
          println(s"val xb: Int = xs.fold (1) ((x, y) => x * y) = ${xb}")
        }
      )
    }
  }
}

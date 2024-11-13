object IterableTrait {
  def make_title(title: String): String = s"// *** $title ***"
  def section_start(title: String) = println(make_title(title))
  def section_end(title: String) = println(make_title(title) + "\n")
  def main(args: Array[String]) = {
    {
      // イテレーション
      val title = "イテレーション"
      section_start(title)

      println("// List の定義")
      val xs = List(1, 2, 3, 4, 5)
      println(s"val xs: List[Int] = $xs")

      println("// grouped")
      val git = xs grouped 3
      println("val git: Iterator[List[Int]] = xs grouped 3 = <iterator>")
      println(s"var g: List[Int] = git.next = ${git.next}")
      println(s"var g: List[Int] = git.next = ${git.next}")

      println("// sliding")
      val sit = xs sliding 3
      println("val sit: Iterator[List[Int]] = xs sliding 3 = <iterator>")
      println(s"var s: List[Int] = sit.next = ${sit.next}")
      println(s"var s: List[Int] = sit.next = ${sit.next}")
      println(s"var s: List[Int] = sit.next = ${sit.next}")

      println("// foreach")
      println("xs foreach (x => printf(s\"squared: $x -> ${x * x}\\n\"))")
      xs foreach (x => printf(s"squared: $x -> ${x * x}\n"))

      section_end(title)
    }

    {
      // concat: 追加
      // コレクションの後ろにコレクション、または、イテレータが生成(yield)するすべての要素を追加する
      val title = "追加 concat"
      section_start(title)

      val xs = List(1, 2, 3, 4, 5)
      println(s"val xs: List[Int] = $xs")

      val ys = List(4, 5, 6)
      println("val xs: List[Int] = List(1, 2, 3, 4, 5)")

      section_end(title)
    }

    // map: 写像
    // コレクションの要素に何らかの関数を適用して新しいコレクションを作る
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // 変換
    // コレクションを受け取って新しいコレクションを返す
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // コピー
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // サイズ情報
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // 要素取得
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // 部分コレクション取得
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // 部分コレクションへの分割
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // 要素テスト
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // 畳み込み
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // 特定の要素型を対象とする畳み込み
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // 文字列作成
    {
      val title = ""
      section_start(title)

      section_end(title)
    }

    // ビュー
    {
      val title = ""
      section_start(title)

      section_end(title)
    }
  }
}

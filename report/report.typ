#import "@preview/cetz:0.2.2"
#import "@preview/fletcher:0.5.1" as fletcher: node, edge
#import "@preview/ctheorems:1.1.2": *
#import "@preview/numbly:0.1.0": numbly
#import "@preview/codelst:2.0.2": sourcecode, sourcefile

#let title = " Scala メタプログラミング "
#let subtitle = "Subtitle"

#let fontSize = (
  default: 12pt,
  title: 16pt,
)
#set text(fontSize.default)
#set text(lang: "ja")
#set text( font: ("IBM Plex Serif", "BIZ UDMincho", "IPAexMincho"))
// #show regex("[\p{scx:Han}\p{scx:Hira}\p{scx:Kana}]"): set text(font: "BIZ UDMincho")
#show raw: set text(
  font: "UDEV Gothic NFLG",
)
#show bibliography: set text(lang: "en")
#show cite: set text(lang: "en")
#set ref(supplement: it => {
  let it-func = it.func()
  if it-func == image{
    [Fig. ]
  } else if it-func == heading{
    []
  } else {
    it.supplement
  }
})
#set page(
  paper: "a4",
  columns: 1,
  numbering: "1",
  number-align: center,
)
#set document(
  title: [#title],
  // author: [#author.flatten()], 
  keywords: (),
  date: datetime.today(),
)

#set heading(numbering: numbly("{1}.", default: "1.1"))

#let emph_red(body) = { 
  show emph: it => {
    set text(red)
    it.body
  }
  emph(body)
}
#let underline_red(body) = { underline(stroke: 1pt + red, [#body]) }
#let scf(x) = {
  sourcefile(read(x), file: x)
}

// begin 
#set text(size: fontSize.default);
#show title: set align(center)
#show title: set text(size: fontSize.default * 1.5)
#title

Scala は関数型言語パラダイムを汲んでおりメタプログラミングをサポートしている。
メタプログラミングについて興味があるため、Scala のメタプログラミングでは何が行えるのか調査を行った。
その中で Scala のメタプログラミングの要素としてインラインが挙げられており、それについて調査を行った。

#outline(
  title: "目次",
  indent: auto,
)

= 概要 <intro>
まず Scala におけるメタプログラミングについて調査を行った。
調査の出発点として Eugene Burmako の
#cite(label("10.1145/2489837.2489840"))
を取り扱う。
Scala 2.10 のリリースの際にマクロの機能が導入された。
この論文はその際に書かれたものである。
これに付随して Scala の公式ドキュメントとして同様に Eugene Burmako によって書かれたものが
#cite(label("scalalangMacros")) 
である。
日本語のドキュメントとして
Eugene Yokota
が翻訳したものが
#cite(label("scalalangMacrosJa"))
として存在する。
これらとは別に Scala 3 に基づいたドキュメントとして
#cite(label("scalalang3Macros"))
#cite(label("scala3Metaprogramming"))
が存在する。

= メタプログラミング <metaprogramming>
Eugene Burmako の
#cite(label("10.1145/2489837.2489840"))
を基にメタプログラミングについて理解する。

コンパイル時メタプログラミングはコンパイル時にプログラムをアルゴリズムで構築するものだと考えることができる。
プログラマーが自分でプログラムの一部を書くのではなくプログラムの一部を生成できるようにする目的で使われることが多い。
つまりメタプログラミングは他のプログラムについての知識を持ち、それらを操作できるプログラムである。

言語やパラダイムを超えて、この種のメタプログラミングは非常に有用である。
下記の技術はこれによって支えられている。
- 言語仮想化 (language virtualization)
  - 元のプログラミング言語のセマンティクスをオーバーロードまたはオーバライドする
- 外部ドメイン固有言語の埋め込み
  - 外部ドメイン固有言語をホスト言語に対して統合する
- 自己最適化
  - プログラム自身のコードの解析に基づいて最適化を自己適用する
- ボイラーテンプレートの生成
  - 基盤となる言語で容易に抽象化できない反復パターンを自動化する

== Scala マクロ <scalamacro>
Scala 2.10 では Scala Macros が導入され、Scala のコンパイル時メタプログラミングを実現した。
これによってコンパイラは Scala プログラム内の特定のメソッドをメタプログラムまたは _マクロ(macros)_ として認識し、それらをコンパイルの特定のタイミングで呼び出せる。
呼び出し時にマクロに対してコンパイラの文脈が渡される。
コンパイラの文脈には、現在コンパイルされているプログラムのコンパイラ表現と解析、型チェック、エラーレポートなどのコンパイラ機能を提供する API が含まれている。
文脈の使用可能な API を使用することでマクロはコンパイルに影響を与えることができる。
例えばコンパイルされるコードを変更したり、型推論に影響を与えるなどである。

= インライン <inline>
インライン化は一般的なコンパイル時のメタプログラミング手法であり、通常はパフォーマンスの最適化を達成するために使用される。
Scala 3 では、インライン化の概念がマクロを使用したプログラミングへの入り口を提供する。

下記はインラインの例である。
具体的には `logging` と `log` が `inline` で宣言されている。
`logging` が `inline` で宣言されているため `log` の `if-then-else` 式の評価はランタイム時ではなくコンパイル時に行われる。
参考: https://docs.scala-lang.org/scala3/reference/metaprogramming/inline.html

#scf("../src/fact.scala")

`logging` の値は `false` になっているためコンパイル後の `log` には `else` 節の `op` のみが含まれる。
`factorial` の中身は下記と等価であるといえる。

#sourcecode(```scala
def factorial(n: BigInt): BigInt =
  if n == 0 then 1
  else n * factorial(n - 1)
```)

`logging` が `true` の場合は下記になる。

#sourcecode(```scala
def factorial(n: BigInt): BigInt =
  val msg = s"factorial($n)"
  println(s"${"  " * indent}start $msg")
  Logger.inline$indent_=(indent.+(indentSetting))
  val result =
    if n == 0 then 1
    else n * factorial(n - 1)
  Logger.inline$indent_=(indent.-(indentSetting))
  println(s"${"  " * indent}$msg = $result")
  result
```)

このときの実行結果は下記になる。

#sourcecode(```
% scala-cli run . --server=false --main-class FactObj
start factorial(3)
  start factorial(2)
      start factorial(1)
          start factorial(0)
          factorial(0) = 1
      factorial(1) = 1
  factorial(2) = 2
factorial(3) = 6
6
```)

`if` 式自体を `inline` で宣言することができ、その場合は `if` 式のどのブランチであるかコンパイル時に決定できる必要がある。
同様に `match` 式も `inline` で宣言することができる。

ここでは `transparent` で宣言しており、これによって返す値の型が特定のものに決定できることを指す。
そのため、下記の `x` は `inline` で宣言することができる。
`transparent` の宣言がないと `toInt` の返却時の型は `Int` になってしまう。
宣言があると `toInt` は特定の型を返すことができ、実際に `x` の型は定数リテラルの `2` である。

#scf("../src/inline-match.scala")

つまり `transparent` 宣言を用いることによってこの場合 `toInt` の返却する型は `Int` ではなく `Int` に属する型リテラルであり、`toInt` の返却する型を透過的に変更している。
型チェックはランタイム時ではなくコンパイル時に実行されるため、`x3` のような表現が可能になる。
実際に `x4` はコンパイル時にエラーになることに加え、コーディング時に Laguage Server を通して静的に検出される。

=  まとめと今後
Scala のメタプログラミングの論文に触れることで、メタプログラミングそのものへの理解と Scala がメタプログラミングの実装、検証として利用されていることが分かった。
実際に Scala のメタプログラミングの要素であるインラインに触れた。
インラインを用いることでランタイム時ではなくコンパイル時にあらかじめ計算することで最適化を行うことができる。
加えて `transparent` を用いることで抽象型で宣言された関数でも透過的に特定の型を返すことができる。

本来は Scala のマクロまで触れたかったが、時間が足らなかったため今後の課題とする。
少し具体的に調査した内容について述べると、マクロを用いることで正しく型づけされた状態で多段階計算を実現することができる。
日本語に記事として
#cite(label("taro"))
が詳細に解説していた。

// = ref 
// #cite(label("10.1145/2489837.2489840"))

= 付録
本レポート上で書かれたコードは https://github.com/blank71/scala-learn しているため、そこから利用することができる。
同様に本レポートの原本もアップロードしてある。


#bibliography(
  "ref.bib",
)

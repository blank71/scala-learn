# Scala コレクション
- Scala のコレクションに関する勉強を行ったので成果物を公開する。スライドは [ここ](./slide/slide.pdf) から参照することができる。

## 参考文献
- Scalaスケーラブルプログラミング 第4版
  - https://book.impress.co.jp/books/1119101190
- https://docs.scala-lang.org/ja/overviews/collections/introduction.html
  - 2025-01-05 参照
  - コレクションに関しての日本語の文献
  - Scalaスケーラブルプログラミングと構成や文がほとんど同じだが、こちらの方が古い
- https://docs.scala-lang.org/overviews/collections-2.13/introduction.html
  - 2025-01-05 参照
  - コレクションに関する文献
  - Scalaスケーラブルプログラミングはこれを訳したものと考えてよい
  - Scala3 のコードが掲載されているが、部分的に関数スタイルのコードが撤廃されている

# Scala インライン
- Scala のメタプログラミングの要素の 1 つであるインラインについて調査した。レポートは [ここ](./report/report.pdf) から参照することができる。

# exec from scala-cli

- `--server=false` を付けないと実行時にエラーを吐いてしまうため、設定している。
- `--main-class` で実行したいクラスを指定する。

```bash
scala-cli run . --server=false --main-class IterableTrait
scala-cli run . --server=false --main-class Par
scala-cli run . --server=false --main-class SeqTrait
scala-cli run . --server=false --main-class SetTrait
scala-cli run . --server=false --main-class MiscTrait
scala-cli run . --server=false --main-class FactObj
scala-cli run . --server=false --main-class InlineMatch
```

# scala-cli version

```bash
% scala-cli version
Scala CLI version: 1.5.1
Scala version (default): 3.5.1
Your Scala CLI version is outdated. The newest version is 1.5.4
It is recommended that you update Scala CLI through the same tool or method you used for its initial installation for avoiding the creation of outdated duplicates.
```
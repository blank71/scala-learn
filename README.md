# exec from scala-cli

- `--server=false` を付けないと実行時にエラーを吐いてしまうため、設定している。
- `--main-class` で実行したいクラスを指定する。

```bash
scala-cli run . --server=false --main-class IterableTrait
scala-cli run . --server=false --main-class Par
scala-cli run . --server=false --main-class SeqTrait
```
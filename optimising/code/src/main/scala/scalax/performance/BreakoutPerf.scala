package scalax.performance

import org.openjdk.jmh.annotations.Benchmark

//[info] Benchmark               Mode  Cnt    Score    Error  Units
//[info] BreakoutPerf.basic     thrpt   20  505.101 ± 34.788  ops/s
//[info] BreakoutPerf.breakout  thrpt   20  531.372 ± 20.994  ops/s
//[success] Total time: 86 s, completed 08-Dec-2015 20:28:54

object BreakoutPerf {
  case class Entry(id: String, value: String)
  val inputs = (1 to 10000).map( i => Entry(s"$i", s"_${i}_")).toList
  var target: Map[String, Entry] = Map.empty
}

class BreakoutPerf {
  import BreakoutPerf._

  @Benchmark
  def basic(): Unit = {
    val result = inputs.map { e => (e.id, e)}.toMap
    target = result
  }

  @Benchmark def breakout(): Unit = {
    import scala.collection.breakOut
    val result : Map[String, Entry] = inputs.map { e => (e.id, e)}(breakOut)
    target = result
  }
}

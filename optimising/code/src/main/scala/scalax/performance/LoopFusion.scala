package scalax.performance

import org.openjdk.jmh.annotations.Benchmark

import scala.collection.mutable.ListBuffer

object LoopFusion {
  val data = (1 to 1000).toList
  var result = List[(Int,Int)]()
}
class LoopFusion {

  import LoopFusion._

  @Benchmark
  def basic(): Unit = {
    val res = for ((item, i) <- data.zipWithIndex; if item % 2 == 0) yield (item, i)
    result = res
  }

  @Benchmark
  def basicOpt(): Unit = {
    val res = for (pair <- data.zipWithIndex; if pair._1 % 2 == 0) yield pair
    result = res
  }

  @Benchmark
  def optimisedBasic(): Unit = {

    import scalaxy.streams.optimize
    optimize {
      val res = for ((item, i) <- data.zipWithIndex; if item % 2 == 0) yield (item, i)
      result = res
    }
  }

  @Benchmark
  def optimisedBasicOpt(): Unit = {

    // slower than optimisedBasic (probably due to conflicting with the optimiser
    import scalaxy.streams.optimize
    optimize {
      val res = for (pair <- data.zipWithIndex; if pair._1 % 2 == 0) yield pair
      result = res
    }
  }

  @Benchmark
  def fused(): Unit = {
    val lb = ListBuffer[(Int,Int)]()

    var i = 0
    var next = data
    while(next != Nil) {
      val item = next.head
      if (item % 2 == 0) {
        lb += i -> item
      }
      next = next.tail
      i += 1
    }

    result = lb.toList
  }

}


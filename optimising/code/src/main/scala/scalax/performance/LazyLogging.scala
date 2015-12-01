package scalax.performance

import org.openjdk.jmh.annotations.Benchmark

object LazyConfig {
  val limit = 1000000  // 1M
  val debugEnabled = Option(System.getProperty("DEBUG")).isDefined

  var tmp = 0
}

object LazyLogging {
  def main(args: Array[String]): Unit = {
    Thread.sleep(15000)
    val ll = new LazyLogging()
    ll.noForLoopGuarded()
    System.gc()
    System.out.println("HERE")
    Thread.sleep(15000)
    ll.noForLoopGuarded()
    Thread.sleep(15000)
  }
}

class LazyLogging {
  import LazyConfig._

  @Benchmark
  def noLogging(): Unit = {
    var x = 0
    for(i <- 1 to limit) {
      x += i
    }
    if(debugEnabled)
      tmp = x
  }

  //-------------------------------------

  def logDebug(s: String): Unit = {
    if(debugEnabled)
      println(s)
  }

  @Benchmark
  def basicLogging(): Unit = {
    var x = 0
    for(i <- 1 to limit) {
      logDebug(s"started iteration $i")
      x += 1
      logDebug(s"finished iteration $i")
    }
    if(debugEnabled)
      tmp = x
  }

  def lazyLogDebug(f: => String): Unit = {
    if(debugEnabled)
      println(f)
  }

  @Benchmark
  def lazyLogging(): Unit = {
    var x = 0
    for(i <- 1 to limit) {
      lazyLogDebug(s"started iteration $i")
      x += 1
      lazyLogDebug(s"finished iteration $i")
    }

    if(debugEnabled)
      tmp = x
  }

  // ------------------

  @Benchmark
  def guardedLogging(): Unit = {
    var x = 0
    for(i <- 1 to limit) {
      if(debugEnabled)
        logDebug(s"started iteration $i")
      x += i
      if(debugEnabled)
        logDebug(s"finished iteration $i")
    }

    if(debugEnabled)
      tmp = x
  }

  @Benchmark
  def noForLoopGuarded(): Unit = {
    var x = 0

    var i = 1
    while(i <= limit) {
      if(debugEnabled)
        logDebug(s"started iteration $i")
      x += i
      if(debugEnabled)
        logDebug(s"finished iteration $i")
      i += 1
    }

    if(debugEnabled)
      tmp = x
  }
}

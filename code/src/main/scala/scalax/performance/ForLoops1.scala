package scalax.performance

object ForLoops1 {

  def main(args: Array[String]): Unit = {

    Thread.sleep(10000)
    var counter = 0
    for(i <- 1 until 1000 * 1000 * 1000 ) {
      counter += 1
    }

    println("counter = " + counter)
  }


}

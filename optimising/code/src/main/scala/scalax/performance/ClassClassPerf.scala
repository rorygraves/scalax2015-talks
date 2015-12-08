//package scalax.performance
//
//import org.openjdk.jmh.annotations.Benchmark
//
//case class ClassOne(s: String, i: Int, d: Double, b: Boolean)
//case class ClassTwo(s: String, i: Int, d: Double, b: Boolean) {}
//
//object CaseClassPerf {
//
//  val classOneList = (1 until 1000).map(i => ClassOne("$i",i,i, i % 2 ==0))
//  val classTwoList = (1 until 1000).map(i => ClassTwo("$i",i,i, i %2 == 0))
//
//  def main(args: Array[String]): Unit = {
//    classOneList.foreach { c =>
//      val hash = c.hashCode()
//      println(hash)
//    }
//  }
//}
//
//class ClassClassPerf {
//
//
//  @Benchmark
//  def test(): Unit = {
//
//
//
//  }
//}

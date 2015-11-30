//package scalax.performance
//
//object CaseClasses1 extends App {
//
// // Thread.sleep(15000)
//  case class Foo(s: String, i : Int)
//
//
//  val x = Foo("1", 100001)
//  val y = Foo("2", 200002)
//
//  x.equals(y)
//  println("Starting test")
//  var i = 0
//  var count = 0
//  while(i< 100000000) { // 10 M
//    if(x.hashCode() != y.hashCode())
//      count += 1
//    i = i + 1
//  }
//
//  println("count = " + count)
//
//
//
//}

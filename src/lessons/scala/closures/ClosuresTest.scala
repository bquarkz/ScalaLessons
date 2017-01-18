package lessons.scala.closures

class ClosuresTest( n: Int ) {
  // argument check
  
  // fields
  private val scl = 1
  val more = ( ( x: Int ) => scl * x )( n )

  { // constructor implementation
  }
  
  // support constructors
  
  // methods
}

object ClosuresTest {
  def main(args: Array[String]): Unit = {
    var ct: ClosuresTest = new ClosuresTest( 0 )
    val inc = ( x: Int ) => { x + ct.more } // it�s a closure
    
    val list = List( 1, 2, 3, 4, 5 )
    var sum1 = 0
    list.foreach( sum1 += inc( _ ) ) // it�s other kind of closure
    println( "sum1 result: " + sum1 )
    
    ct = new ClosuresTest( 1 )
    var sum2 = 0
    list.foreach( sum2 += inc( _ ) )
    println( "sum2 result: " + sum2 )
    
    // the open variable will ever index "ct" as a reference and will 
    // take the value from what it�s indexing 

    println( "number of elements: " + list.size )
    println( "sum2 - sum1 = " + ( sum2 - sum1 ) )
  }
}
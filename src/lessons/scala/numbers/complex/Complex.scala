package lessons.scala.numbers.complex

class Complex( real:Double, imag:Double ) {
  // argument check
  
  // fields
  val r = real
  val i = imag
  val norma = transform( ( x, y ) => { x * x + y * y }, 0 )
  val module = scala.math.sqrt( norma )
  
  { // constructor implementation
    println( this.toString )
  }
  
  // support constructors
  final def this( r:Double ) = { this( r, 0 ) }
  
  // methods
  override def toString:String = "[Complex Number] ( " + r + " ) + ( " + i + " )i"
  
  final def +( r:Double ):Complex = { this + Complex( r ) }
  final def -( r:Double ):Complex = { this - Complex( r ) }
  final def *( r:Double ):Complex = { this * Complex( r ) }
  final def /( r:Double ):Complex = { this / Complex( r ) }
  
  final def +( c:Complex ):Complex = { Complex( r + c.r, i + c.i ) }
  final def -( c:Complex ):Complex = { Complex( r - c.r, i - c.i ) }
  final def *( c:Complex ):Complex = { Complex( r * c.r - i * c.i, r * c.i + i * c.r ) }
  final def /( c:Complex ):Complex = { require( c.r != 0.0 || c.i != 0.0 ); this * Complex( c.r / c.norma, -c.i / c.norma ) }
  
  final def ==( c:Complex ):Boolean = { ( r == c.r ) && ( i == c.i ) }
  final def !=( c:Complex ):Boolean = { !( this == c ) }
  final def >( c:Complex ):Boolean = { module > c.module }
  final def <( c:Complex ):Boolean = { module < c.module }
  final def >=( c:Complex ):Boolean = { ( this == c ) || ( this > c ) }
  final def <=( c:Complex ):Boolean = { ( this == c ) || ( this < c ) }
  
  // just a simple example to explain how to receive a function literal as a parameter
  final private def transform(
      f: // Name of the parameter function
      ( Double, Double ) // Parameters of the parameter function
      => 
      Double // Return of the parameter function
      , x: Double // Other parameters...
  ):Double = { f( r, i ) + x }
}

object Complex {
  final def apply( r: Double, i: Double ): Complex = new Complex( r, i )
  final implicit def apply( r: Double ): Complex = new Complex( r )
  
  def calculate( m1:Double, m2:Double, dist:Double ): Double = {
		  ( m2 * dist ) / ( m1 + m2 )
  }
  
  def main(args: Array[String]): Unit = {
    val c1 = Complex( 1, 0 )
    val c2 = Complex( 3, 4 )
    
    println( c1 == c2 )
    println( c1 > c2 )
    println( c1 < c2 )
    println( c1 != c2 )
    println( c1 >= c2 )
    println( c1 <= c2 )
    
    val dists = List( 2.0, 4.0, 6.0, 8.0, 10.0 )
    val m1 = 1.0
    var m2 = m1
    val c = calculate( m1, m2, _:Double )
    
    dists.foreach( x => { println( "dist: " + c( x ) ) } )
    m2 = 1000
    dists.foreach( x => { println( "dist: " + c( x ) ) } )
  }
}
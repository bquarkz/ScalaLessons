package lessons.scala.numbers.rational

import scala.annotation.tailrec

class Rational( n: Int, d: Int ) {
  // Check Arguments
  require( d != 0 )
  
  // Fields
  private val g = greaterCommonDivisor( n, d )
  val numerator = n / g
  val denominator = d / g
  
  { // Constructor Implementation
    println( this.toString )
  }
  
  // Support Constructors
  final def this( n: Int ) = this( n, 1 )
  
  // Methods
  override def toString = { "Rational Number: GCD[ " + g + " ] : (" + n + ")" + numerator + " / " + denominator + "(" + d + ")" }
  
  final def greaterCommonDivisor( a: Int, b: Int ): Int = {
    @tailrec
    def gcd( a: Int, b: Int ): Int = {
	    if( b == 0 ) a else gcd( b, a % b )
    }
    
    if( a == b ) a.abs
    else if( a > b ) gcd( a.abs, b.abs ) else gcd( b.abs, a.abs ) 
  }
  

  final def +( o: Int ): Rational = { this + new Rational( o ) }
  final def -( o: Int ): Rational = { this - new Rational( o ) }
  final def *( o: Int ): Rational = { this * new Rational( o ) }
  final def /( o: Int ): Rational = { this / new Rational( o ) }
  
  final def ^( o: Int ): Rational = {
    def powerOf( i: Int, r: Rational ): Rational = { if( i >= o ) r else this * powerOf( i + 1, this ) }
    powerOf( 1, this )
  }
  
  final def +( o: Rational ): Rational = { new Rational( numerator * o.denominator + denominator * o.numerator, denominator * o.denominator ) }
  final def -( o: Rational ): Rational = { new Rational( numerator * o.denominator - denominator * o.numerator, denominator * o.denominator ) }
  final def *( o: Rational ): Rational = { new Rational( numerator * o.numerator, denominator * o.denominator ) }
  final def /( o: Rational ): Rational = { new Rational( numerator * o.denominator, denominator * o.numerator ) }
}

object Rational {
  final implicit def apply( o:Int ): Rational = new Rational( o )
  final def apply( n: Int, d: Int ): Rational = new Rational( n, d )
	
  def main(args: Array[String]): Unit = {
    val r1 = Rational( 1, 2 )
    val r2 = Rational( 1, 2 )
    r1 + r2
    r1 - r2
    r1 * r2
    r1 / r2
    r1^2
    
    println( "=========" )
    
    r1 + 2
    r1 - 2
    r1 * 2
    r1 / 2
    
    println( "=========" )
    
    2 + r1
    2 - r1
    2 * r1
    2 / r1
  }
  
}
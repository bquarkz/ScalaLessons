package lessons.scala.intqueue

import scala.collection.mutable.ArrayBuffer

// 
abstract class IntQueue {
  // argument check
  
  // fields

  { // constructor implementation
    println( "==== IntQueue Constructor ====" )
  }
  
  // support constructors
  
  // methods
  def put( element: Int ): Unit
  def get: Int 
}

object IntQueue {
  val openBracket = "+";
  val closeBracket = "-";
  
  // este extends quer dizer que Doubling pode ser usado somente em classes que sejam IntQueue
  private trait Doubling extends IntQueue {
    {
      println( "==== Doubling Constructor ====" )
    }
    
    abstract override def put( element: Int ) = {
      println( openBracket + " Doubling" )
      super.put( 2 * element )
      println( closeBracket + " Doubling" )
    }
  }
  
  private trait Incrementing extends IntQueue {
    {
      println( "==== Incrementing Constructor ====" )
    }

    abstract override def put( element: Int ) = {
      println( openBracket + " Incrementing" )
      super.put( element + 1 )
      println( closeBracket + " Incrementing" )
    }
  }
  
  private trait Returning extends Incrementing {
    {
      println( "==== Returning Constructor ====" )
    }

    abstract override def put( element: Int ) = {
      println( openBracket + " Returning" )
      super.put( element - 1 )
      println( closeBracket + " Returning" )
    }
  }

  
  private trait Filtering extends IntQueue {
    {
      println( "==== Filtering Constructor ====" )
    }

    abstract override def put( element: Int ) = {
      println( openBracket + " Filtering" )
      if( element >= 0 ) super.put( element )
      println( closeBracket + " Filtering" )
    }
  }
  
  private class BasicIntQueue extends IntQueue {
    // argument check
    
    // fields
    private val queue = new ArrayBuffer[ Int ]
  
    {
      println( "==== BasicIntQueue Constructor ====" )
    }
    
    // support constructors
    
    // methods
    def put( element: Int ): Unit = {
      println( "====== BasicIntQueue: Added ======" )
      queue += element
    }
    def get: Int = queue.remove( 0 )
    override def toString: String = queue.toString
  }
  
  private class MyQueue extends BasicIntQueue with Doubling with Incrementing with Filtering
  
  def create: IntQueue = new MyQueue
  
  def main(args: Array[String]): Unit = {
    def prt( qs: Array[ IntQueue ])( a: Array[ Int ] ): String = {
     		qs.foreach( ( q: IntQueue )=> a.foreach( q.put( _ ) ) )
 		    ( for( i <- 0 until qs.length ) yield qs( i ) ).toString
    }
    
 		val qs: Array[ IntQueue ] = new Array[ IntQueue ]( 2 )
 		println( "0: =======================" )
 		qs( 0 ) = new BasicIntQueue with Returning
 		println( "1: =======================" )
 		qs( 1 ) = new BasicIntQueue with Filtering
 		val prtqs = prt( qs )_
 		
 		val a = Array( 0, -1, 2 )
 		println( prtqs( a ) )
  }
}
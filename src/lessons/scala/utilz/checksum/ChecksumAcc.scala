package lessons.scala.utilz.checksum

class ChecksumAcc {
  private var sum = 0
  def add( b: Byte ) { sum += b }
  def checksun(): Int = { ~( sum & 0xFF ) + 1 }
}

object ChecksumAcc {
  private val cache = scala.collection.mutable.Map[ String, Int ]()
  
  def calculate( s: String ) : Int = {
    if( cache.contains( s ) ) cache( s )
    else {
      val acc = new ChecksumAcc()
      s.foreach( c => { acc.add( c.toByte ) } )
      val cs = acc.checksun()
      cache += ( s -> cs )
      cs
    }
  }
}
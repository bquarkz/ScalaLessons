package lessons.scala.elements

abstract class Element {

  def content: Array[ String ] //abstract method
  def height: Int = content.length
  def width: Int = if( height == 0 ) 0 else content( 0 ).size
}

class ArrayElement( override val content: Array[ String ] ) extends Element { // changing content to val
  
}

class LineElement( s: String ) extends Element {
  
  override val content: Array[ String ] = Array( s )
  override val height: Int = 1
  override val width: Int = s.length
}

class UniformElement(
    ch: Char,
    override val height: Int,
    override val width: Int
                    ) extends Element {
  
  private val line: String = ch.toString * width
  override val content: Array[ String ] = Array.fill( height )( line )
}
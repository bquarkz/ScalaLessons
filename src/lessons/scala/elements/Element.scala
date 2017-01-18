package lessons.scala.elements

abstract class Element {
  private val charFiller: Char = ' '
  
  def content: Array[ String ] //abstract method
  def height: Int = content.length
  def width: Int = if( height == 0 ) 0 else content( 0 ).size
  
  def above( e: Element ): Element = {
    val e1 = this.widthAdjust( e.width )
    val e2 = e.widthAdjust( this.width )
    
    Element.create( e1.content ++ e2.content );
  }
  
  def beside( e: Element ): Element = {
    val e1 = this.heightAdjust( e.height )
    val e2 = e.heightAdjust( this.height )
    
    val r = for( ( l1, l2 ) <- e1.content zip e2.content ) yield l1 + l2 

    Element.create( r )
  }
  
  override def toString: String = this.content mkString "\n"
  
  private def widthAdjust( w: Int ): Element = {
    if( w <= this.width )
      this
    else {
      val dif: Int = w - this.width
      val left = Element.create( charFiller, dif / 2, this.height )
      val right = Element.create( charFiller, dif - left.width, this.height )
      
      left beside this beside right
    }
  }
  
  private def heightAdjust( h: Int ): Element = {
    if( h <= this.height )
      this
    else {
      val dif: Int = h - this.height
      val up = Element.create( charFiller, this.width, dif / 2 )
      val down = Element.create( charFiller, this.width, dif - up.height )
      
      up above this above down
    }
  }
}

object Element {
  private class ArrayElement( override val content: Array[ String ] ) extends Element {
  }
  
  private class LineElement( s: String ) extends Element {
    
    override val content: Array[ String ] = Array( s )
    override val height: Int = 1
    override val width: Int = s.length
  }
  
  private class UniformElement(
      ch: Char,
      override val width: Int,
      override val height: Int
                      ) extends Element {
    
    private val line: String = ch.toString * width
    override val content: Array[ String ] = Array.fill( height )( line )
  }

  def create( content: Array[ String ] ): Element = new ArrayElement( content )
  def create( s: String ): Element = new LineElement( s )
  def create( ch: Char, width: Int, height: Int ): Element = new UniformElement( ch, width, height )
}
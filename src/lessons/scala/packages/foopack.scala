package lessons.scala.packages

package navigation {
  
  private[ packages ] class Navigator {
    // será acessivel em Navigator
    // será acessivel via herança
    // será acessivel em todo o package: "packages"
    protected[ packages ] def useStarChart: Unit = {}
    
    class LegOfJourney {
      // sera acessivel em LegOfJourney
      // não será acessivel via herança
      // será acessivel para outros elementos dentro de Navigator
      private[ Navigator ] val distance = 100
      
      // não é possivel pois Vehicle não se encontra na mesma
      // arvore de "LegOfJourney" que no caso é:
      // LegOfJourney -> Navigator -> navigation -> packages
      // para o caso de Vehicle:
      // Vehicle -> launch -> packages
      //private[ Vehicle ] val distance2 = 100
      
      // sera acessivel somente em LegOfJourney
      // não será acessivel via herança
      private[ this ] val sofar = 100000
    }
  }
}

package launch {
  // pode usar os nomes de navigation sem necessitar de colocar o "navigation."
  import navigation._
  
  object Vehicle {
    private[ launch ] val guide = new Navigator
    guide.useStarChart
  }
  
}

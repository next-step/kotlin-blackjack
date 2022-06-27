package blackjack.entity

class Card(val shape: Shape, val number: CardNumber){
  fun getShapeValue(): String {
    return shape.value
  }
  fun getNumberValue(): Int {
    return number.value
  }
}

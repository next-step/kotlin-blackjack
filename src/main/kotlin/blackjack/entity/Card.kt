package blackjack.entity

class Card (val shape:Shape, val number: Int){
  companion object{
    const val MINIMUM_CARD_NUMBER = 1
    const val MAXIMUM_CARD_NUMBER = 14
  }
}
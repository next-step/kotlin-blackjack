package blackjack.model

class Card(val cardValue: CardValue, private val suit: CardSuit){
    fun getName() = "${cardValue.value}${suit.suitName}"
}

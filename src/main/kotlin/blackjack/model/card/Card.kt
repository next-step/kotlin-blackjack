package blackjack.model.card

data class Card(val number: CardNumber, val suit: CardSuit) {
    val score = number.score
}

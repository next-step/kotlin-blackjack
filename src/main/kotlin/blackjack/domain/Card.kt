package blackjack.domain

data class Card(val cardNumber: CardNumber, val suit: Suit) {

    fun getScore(isAceEleven: Boolean = false): Int {
        return cardNumber.score
    }
}

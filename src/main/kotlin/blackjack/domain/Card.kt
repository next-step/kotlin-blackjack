package blackjack.domain

data class Card(
    val number: CardNumber,
    val suit: CardSuit
) {

    fun isAce(): Boolean {
        return number == CardNumber.ACE
    }
}

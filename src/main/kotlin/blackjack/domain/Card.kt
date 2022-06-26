package blackjack.domain

data class Card(
    val cardNumber: CardNumber,
    val cardSuit: CardSuit
) {

    fun isAce(): Boolean {
        return cardNumber == CardNumber.ACE
    }
}

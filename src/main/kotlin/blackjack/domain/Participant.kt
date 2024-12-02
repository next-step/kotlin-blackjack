package blackjack.domain

abstract class Participant(
    val name: String,
    val cards: Cards = Cards(),
    var isBlackJack: Boolean = false,
) {
    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun checkBlackJack() {
        if (cards.isBlackJack()) {
            isBlackJack = true
        }
    }

    abstract fun canDrawCard(): Boolean
}

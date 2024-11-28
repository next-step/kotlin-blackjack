package blackjack.domain

abstract class Participant(
    val name: String,
    val cards: Cards = Cards(),
) {
    fun receiveCard(card: Card) {
        cards.add(card)
    }

    abstract fun canDrawCard(): Boolean
}

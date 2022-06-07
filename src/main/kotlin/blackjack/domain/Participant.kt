package blackjack.domain

data class Participant(
    val name: String
) {
    private val playerCards = Cards()

    val cards: Cards
        get() = playerCards

    fun addCard(card: Card) {
        playerCards.addCard(card)
    }
}

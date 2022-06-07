package blackjack.domain

data class Participant(
    val name: String,
    val playerCards: Cards = Cards()
) {
    fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }
}

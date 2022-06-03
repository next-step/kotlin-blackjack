package blackjack.domain

class CardGame(
    private val players: Players,
    private val cardDeck: CardDeck = CardDeck.new()
) {
    fun distribute(count: Int) {
        repeat(count) {
            distribute()
        }
    }

    private fun distribute() {
        players.list.forEach {
            it.addCard(cardDeck.draw())
        }
    }
}

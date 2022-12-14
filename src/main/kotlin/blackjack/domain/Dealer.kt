package blackjack.domain

data class Dealer(
    override val name: String = "dealer",
    override val myCards: Cards = Cards()
) : Participant(name, myCards) {

    private val cardDeck: CardDeck = CardDeck()

    fun draw(): Card = cardDeck.draw()

    fun distribute(players: List<Player>) {
        repeat(players.size) {
            val player = players[it]
            repeat(INITIAL_CARD_DECK_SIZE) { player.receive(draw()) }
        }
    }

    companion object {
        private const val INITIAL_CARD_DECK_SIZE = 2
    }
}

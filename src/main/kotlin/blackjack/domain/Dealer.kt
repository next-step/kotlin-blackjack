package blackjack.domain

data class Dealer(
    override val name: String,
    override val myCards: Cards = Cards()
) : Participant(name, myCards) {

    private val cardDeck: CardDeck = CardDeck()

    fun draw(): Card = cardDeck.draw()

    fun distribute(players: List<Participant>) {
        repeat(players.size) {
            val player = players[it]
            repeat(INITIAL_CARD_DECK_SIZE) { player.receive(draw()) }
        }

        repeat(INITIAL_CARD_DECK_SIZE) { this.receive(draw()) }
    }

    override fun canDraw(): Boolean = myCards.totalScore <= 16

    companion object {
        private const val INITIAL_CARD_DECK_SIZE = 2
    }
}

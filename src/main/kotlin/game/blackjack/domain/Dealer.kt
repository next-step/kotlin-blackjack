package game.blackjack.domain

class Dealer : Player("딜러", 0) {
    private val deck = Deck()

    fun drawCard(): Card = deck.draw()
    fun drawCard(count: Int): List<Card> {
        val drawn = mutableListOf<Card>()
        repeat(count) { drawn.add(drawCard()) }
        return drawn
    }

    override fun receiveUntilHit(
        action: (name: String) -> Boolean,
        showPlayerCard: (player: Player) -> Unit,
        drawCard: () -> Card
    ) {
        while (cards.score() <= CAN_RECEIVE_SCORE) {
            receive(drawCard())
            showPlayerCard(this)
        }
    }

    companion object {
        val CAN_RECEIVE_SCORE = Score(16)
    }
}

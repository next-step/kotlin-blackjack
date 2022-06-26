package game.blackjack.domain

class Dealer : Player("딜러") {
    private val deck = Deck()

    fun drawCard(): Card = deck.draw()

    override fun canReceive(): Boolean = cards.score() <= CAN_RECEIVE_SCORE

    override fun receiveUntilHit(
        action: (name: String) -> Boolean,
        showPlayerCard: (player: Player) -> Unit,
        drawCard: () -> Card
    ) {
        super.receiveUntilHit({ canReceive() }, showPlayerCard, drawCard)
    }

    companion object {
        val CAN_RECEIVE_SCORE = Score(16)
    }
}

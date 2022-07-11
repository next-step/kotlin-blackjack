package game.blackjack.domain

class Dealer : Participant("딜러") {
    private val deck = Deck()

    fun drawCard(): Card = deck.draw()

    fun drawCard(count: Int): List<Card> {
        return (1..count).map { drawCard() }
    }

    override fun receiveUntilHit(
        action: (name: String) -> Boolean,
        showPlayerCard: (participant: Participant) -> Unit,
        drawCard: () -> Card
    ) {
        while (hand.score() <= CAN_RECEIVE_SCORE) {
            receive(drawCard())
            showPlayerCard(this)
        }
    }

    companion object {
        val CAN_RECEIVE_SCORE = Score(16)
    }
}

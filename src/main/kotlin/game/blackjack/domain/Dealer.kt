package game.blackjack.domain

class Dealer : Player("딜러") {
    private val deck = Deck()

    fun drawCard(): Card = deck.draw()

    fun recordWin() {
        winningRecord.recordWin()
    }

    fun recordLose() {
        winningRecord.recordLose()
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

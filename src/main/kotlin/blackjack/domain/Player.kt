package blackjack.domain

class Player(
    private val name: PlayerName,
    private val hand: Hand,
) : Participant(name, hand) {
    override fun isDrawable(): Boolean {
        return !state.isFinished() && !hand.isBust()
    }

    override fun getInitialCard(): List<Card> {
        return hand.getSpecificRangeCards(0, 1)
    }

    fun compareWithDealer(dealer: Dealer): GameMatchResult {
        return when {
            calculateTotal() > dealer.calculateTotal() -> GameMatchResult.WIN
            calculateTotal() < dealer.calculateTotal() -> GameMatchResult.LOSE
            else -> GameMatchResult.DRAW
        }
    }

    companion object {
        fun createNew(
            playerName: PlayerName,
            cards: List<Card>,
        ): Player {
            return Player(playerName, Hand.createInitial(cards))
        }
    }
}

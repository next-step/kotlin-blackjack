package blackjack.domain

import blackjack.domain.state.Initial

class Player(
    private val name: PlayerName,
    val bettingMoney: BettingMoney,
) : Participant(name) {
    override fun isDrawable(): Boolean {
        return !state.isFinished() && !state.hand().isBust()
    }

    override fun getInitialCard(): List<Card> {
        return state.hand().getSpecificRangeCards(0, 2)
    }

    fun compareWithDealer(dealer: Dealer): GameMatchResult {
        return when {
            dealer.isBust() -> GameMatchResult.WIN
            calculateTotal() > dealer.calculateTotal() -> GameMatchResult.WIN
            calculateTotal() < dealer.calculateTotal() -> GameMatchResult.LOSE
            else -> GameMatchResult.DRAW
        }
    }

    companion object {
        fun createNew(
            playerName: PlayerName,
            bettingMoney: BettingMoney,
            cards: List<Card>,
        ): Player {
            val player = Player(playerName, bettingMoney)
            player.state = Initial.initialState(Hand.createInitial(cards))
            return player
        }
    }
}

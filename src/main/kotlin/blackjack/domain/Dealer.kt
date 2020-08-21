package blackjack.domain

import blackjack.domain.Game.Companion.DEFAULT_CARD_COUNT
import blackjack.domain.Profit.Companion.ZERO_PROFIT

data class Dealer(
    val deck: Deck = Deck(),
    private val basePlayer: Participant = Player()
) : Participant by basePlayer {

    var result: Profit = ZERO_PROFIT
        private set

    fun pickCard(): Card = deck.provideCard()

    fun setUpWithCards() {
        repeat(DEFAULT_CARD_COUNT) { draw(pickCard()) }
    }

    fun pickCardsForPlayers(playerSize: Int): List<Card> =
        (0 until playerSize * DEFAULT_CARD_COUNT).map { pickCard() }

    fun hasScoreBelow17(score: Int): Boolean = score < DEALER_HIT_CRITERIA_SCORE

    fun checkWin(state: State, players: Players): Profit {
        when (state) {
            State.BUST -> players.winIfStillAlive()
            State.BLACKJACK -> players.calculateProfitWhenDealerIsBlackJack()
            else -> players.compareWith(this.score())
        }

        result = Profit(-players.sumOfAllProfits())
        return result
    }

    override fun toString(): String = DEALER_NAME

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_CRITERIA_SCORE = 17
    }
}

package blackjack.business.participants

import blackjack.business.card.CardDesk
import blackjack.business.util.Money

class Dealer(playerCards: PlayerCards = PlayerCards()) : Player(DEALER_NAME, playerCards) {

    override fun canDrawCard(): Boolean = playerCards.sum() < DEALER_DRAW_CONDITION

    fun getPlayerResult(gamePlayer: GamePlayer): PlayerResult {
        val scoreDifference = gamePlayer.score - score
        val resultMoney = when {
            gamePlayer.isBust() -> gamePlayer.money.lose()
            isBust() -> gamePlayer.money * BUST_MULTIPLIER
            isDraw(scoreDifference) -> Money()
            isLose(scoreDifference) -> gamePlayer.money.lose()
            gamePlayer.isNaturalBlackJack() -> gamePlayer.money * NATURAL_BLACKJACK_MULTIPLIER
            else -> gamePlayer.money * BUST_MULTIPLIER
        }
        return PlayerResult(gamePlayer.name, resultMoney)
    }

    private fun isLose(scoreDifference: Int) = scoreDifference < NO_SCORE_DIFFERENCE

    private fun isDraw(scoreDifference: Int) = scoreDifference == NO_SCORE_DIFFERENCE

    fun executeCardDraws(cardDesk: CardDesk, announcer: () -> Unit) {
        if (canDrawCard()) {
            announcer()
            addCard(cardDesk.draw())
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val BUST_MULTIPLIER = 2.0
        const val NATURAL_BLACKJACK_MULTIPLIER = 2.5
        const val NO_SCORE_DIFFERENCE = 0
        private const val DEALER_DRAW_CONDITION = 17
    }
}

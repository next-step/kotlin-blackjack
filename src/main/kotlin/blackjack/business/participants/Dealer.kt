package blackjack.business.participants

import blackjack.business.card.CardDesk

class Dealer(playerCards: PlayerCards = PlayerCards()) : Player(DEALER_NAME, playerCards) {

    override fun canDrawCard(): Boolean = playerCards.canDrawCardWithValueLimit(DEALER_DRAW_CONDITION)

    fun getPlayerResult(gamePlayer: GamePlayer): PlayerResult {
        val scoreDifference = gamePlayer.getScoreDifferent(score)
        val profitOrLoss = when {
            gamePlayer.isBust() -> gamePlayer.lose()
            isBust() -> gamePlayer.calculateMultiplierResult(BUST_MULTIPLIER)
            isDraw(scoreDifference) -> DRAW_RESULT
            isLose(scoreDifference) -> gamePlayer.lose()
            gamePlayer.isNaturalBlackJack() -> gamePlayer.calculateMultiplierResult(
                NATURAL_BLACKJACK_MULTIPLIER
            )

            else -> gamePlayer.calculateMultiplierResult(BUST_MULTIPLIER)
        }
        return PlayerResult(gamePlayer.name, profitOrLoss)
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
        const val DRAW_RESULT = 0
        const val BUST_MULTIPLIER = 2.0
        const val NATURAL_BLACKJACK_MULTIPLIER = 2.5
        const val NO_SCORE_DIFFERENCE = 0
        private const val DEALER_DRAW_CONDITION = 17
    }
}

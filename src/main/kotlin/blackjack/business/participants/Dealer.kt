package blackjack.business.participants

import blackjack.business.card.CardDesk

class Dealer(playerCards: PlayerCards = PlayerCards()) : Player(DEALER_NAME, playerCards) {

    override fun canDrawCard(): Boolean = playerCards.sum() < DEALER_DRAW_CONDITION

    fun getPlayerResult(gamePlayer: GamePlayer): PlayerResult {
        val scoreDifference = gamePlayer.score - score
        val profitOrLoss = when {
            gamePlayer.isBust() -> gamePlayer.money.lose()
            isBust() -> (gamePlayer.money.value * BUST_MULTIPLIER).toInt()
            isDraw(scoreDifference) -> DRAW_RESULT
            isLose(scoreDifference) -> gamePlayer.money.lose()
            gamePlayer.isNaturalBlackJack() -> (gamePlayer.money.value * NATURAL_BLACKJACK_MULTIPLIER).toInt()
            else -> (gamePlayer.money.value * BUST_MULTIPLIER).toInt()
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

package blackjack.business.participants

import blackjack.business.card.CardDesk
import blackjack.business.util.Money

class Dealer(playerCards: PlayerCards = PlayerCards()) : BasePlayer(DEALER_NAME, playerCards) {

    override fun canDrawCard(): Boolean = playerCards.sum() < DEALER_DRAW_CONDITION

    fun getPlayerResult(gamePlayer: GamePlayer): PlayerResult {
        val scoreDifference = gamePlayer.score - score
        val resultMoney = when {
            gamePlayer.isBust() -> gamePlayer.money.lose()
            isBust() -> gamePlayer.money * 2.0
            isDraw(scoreDifference) -> Money()
            isLose(scoreDifference) -> gamePlayer.money.lose()
            gamePlayer.isNaturalBlackJack() -> gamePlayer.money * 2.5
            else -> gamePlayer.money * 2.0
        }
        return PlayerResult(gamePlayer.name, resultMoney)
    }

    private fun isLose(scoreDifference: Int) = scoreDifference < 0

    private fun isDraw(scoreDifference: Int) = scoreDifference == 0

    fun executeCardDraws(cardDesk: CardDesk, announcer: () -> Unit) {
        if (canDrawCard()) {
            announcer()
            addCard(cardDesk.draw())
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CONDITION = 17
    }
}

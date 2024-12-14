package blackjack.domain.result

import blackjack.domain.card.Cards

class GameResultJudge {

    fun judge(playerCards: Cards, dealerCards: Cards): GameResultType {
        if (playerCards.isTwoCardBlackjack()) {
            return if (dealerCards.isTwoCardBlackjack()) {
                GameResultType.PUSH
            } else {
                GameResultType.BLACKJACK_WIN
            }
        }

        if (dealerCards.isBusted()) {
            return GameResultType.WIN
        }

        if (playerCards.isBusted()) {
            return GameResultType.LOSE
        }

        return when {
            playerCards.sum() == dealerCards.sum() -> GameResultType.PUSH
            playerCards.sum() > dealerCards.sum() -> GameResultType.WIN
            else -> GameResultType.LOSE
        }
    }

}

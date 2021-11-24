package blackject.model

import blackject.model.card.Cards

class Dealer(
    cards: Cards = Cards()
) : Person(NAME, cards) {

    override fun isPersonType(): PersonType = PersonType.DEALER

    override fun canTakeMoreCard(): Boolean {
        return MAX_NUMBER_DEALER >= cards.getResultNumber()
    }

    fun calculateProfit(participantAmount: Int, participantProfit: Int): Int {
        return when (result) {
            ResultType.Bust -> 0
            else -> participantAmount.minus(participantProfit)
        }
    }

    override fun calculateGameResult(winScore: Int?, isDealerBust: Boolean, isDealerBlackJack: Boolean) {
        val score = getScore()
        when {
            cards.isBlackjack(score) -> changeResultType(ResultType.BlackJack)
            cards.isBust(score) -> changeResultType(ResultType.Bust)
            score == winScore -> changeResultType(ResultType.Win)
            else -> changeResultType(ResultType.Lose)
        }
    }

    companion object {
        const val MAX_NUMBER_DEALER = 16
        const val NAME = "딜러"
    }
}

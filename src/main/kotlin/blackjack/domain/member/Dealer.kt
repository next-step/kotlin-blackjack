package blackjack.domain.member

import blackjack.domain.Cards
import blackjack.domain.Deck

class Dealer(
    override val cards: Cards
) : Member {
    override val name: String
        get() = DEFAULT_NAME

    override fun ableMoreDrawCard() = resultScore() < DRAW_LIMIT_SCORE
    override fun conditionOfWin(otherMember: Member): Boolean {
        if (isEqualNumberThan(otherMember)) {
            return true
        }

        return isNearBlackJackThan(otherMember)
    }

    fun benefit(gameResultPlayers: ResultPlayers): Double {
        return gameResultPlayers.items.fold(0.0) { sum, resultPlayer -> sum + resultPlayer.benefit() }.unaryMinus()
    }

    companion object {
        fun init(deck: Deck): Dealer {
            val cards = deck.drawInitAssignCards()
            return Dealer(cards)
        }

        private const val DRAW_LIMIT_SCORE = 17
        private const val DEFAULT_NAME = "딜러"
    }
}

package blackjack.domain.model.player

import blackjack.domain.model.Cards
import blackjack.domain.model.Name
import blackjack.domain.model.WinDrawLose
data class Gambler(
    override val name: Name,
    override val cards: Cards
) : AbstractPlayer() {

    fun winDrawLose(dealer: Dealer): WinDrawLose {

        val gamblerScore = cards.score()
        val dealerScore = dealer.cards.score()

        return when {
            gamblerScore.isBust() -> WinDrawLose.LOSE
            dealerScore.isBust() || gamblerScore isHigherThan dealerScore.value -> WinDrawLose.WIN
            dealerScore isHigherThan gamblerScore.value -> WinDrawLose.LOSE
            else -> WinDrawLose.DRAW
        }
    }

    override fun shouldDraw(): Boolean {
        val score = cards.score()
        return !score.isBlackjackScore() && !score.isBust()
    }

    override fun getFirstOpenedCards(): Cards {
        require(cards.values.size > FIRST_OPENED_HAVE_MIN_CARD_SIZE) {
            "겜블러가 처음 카드를 보여줄 경우 ${FIRST_OPENED_HAVE_MIN_CARD_SIZE}보다 많은 카드를 가지고 있어야 한다."
        }
        return cards
    }

    companion object {
        private const val FIRST_OPENED_HAVE_MIN_CARD_SIZE = 2
        fun from(name: Name, cards: Cards = Cards.empty()): Gambler = Gambler(name, cards)
    }
}

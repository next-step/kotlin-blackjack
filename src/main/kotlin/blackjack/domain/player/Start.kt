package blackjack.domain.player

import blackjack.domain.calculator.BlackjackCalculatorFactory
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.player.Blackjack.Companion.BLACKJACK_SCORE

class Start(cards: Cards) : Status(cards) {
    override fun draw(deck: Deck): Status {
        val cards = Cards(listOf(deck.draw(), deck.draw()))
        val calculator = BlackjackCalculatorFactory.getCalculator(cards)
        val calculateScore = cards.calculateScore(calculator)
        if (calculateScore == BLACKJACK_SCORE) {
            return Blackjack(cards)
        }
        return Hit(cards)
    }

    override fun stay(): Status {
        throw IllegalArgumentException("시작도 안한 상태에서 중지는 불가합니다.")
    }

    override fun isFinished(): Boolean {
        return false
    }
}

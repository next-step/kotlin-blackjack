package blackjack.domain.calculator

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards

interface BlackjackCalculator {
    fun isAssignablefrom(cards: Cards): Boolean

    fun calculate(cards: List<Card>): Int
}

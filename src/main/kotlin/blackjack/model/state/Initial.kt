package blackjack.model.state

import blackjack.model.BetMoney
import blackjack.model.score.Score
import blackjack.model.trump.Card
import blackjack.model.trump.Cards

class Initial(override val cards: Cards = Cards()) : State {
    override val score: Score
        get() = cards.score

    override fun add(card: Card): State {
        val cards = cards + card

        if (cards.initializing) {
            return Initial(cards)
        }

        if (cards.isBlackJack) {
            return BlackJack(cards)
        }

        return Hit(cards)
    }

    override fun calculateRevenue(betMoney: BetMoney): BetMoney = BetMoney.ZERO
}

package blackjack.model.state

import blackjack.model.BetMoney
import blackjack.model.score.Score
import blackjack.model.trump.Card
import blackjack.model.trump.Cards

class Hit(override val cards: Cards) : State {
    override val score: Score
        get() = cards.score

    override fun add(card: Card): State {
        val cards = cards + card

        if (cards.isValid) {
            return Hit(cards)
        }

        return Bust(cards)
    }

    override fun calculateRevenue(betMoney: BetMoney): BetMoney = betMoney
}

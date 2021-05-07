package blackjack.model.state

import blackjack.model.BetMoney
import blackjack.model.score.Score
import blackjack.model.trump.Card
import blackjack.model.trump.Cards

class Bust(override val cards: Cards) : State {
    override val score: Score
        get() = cards.score

    override fun add(card: Card): State {
        return this
    }

    override fun calculateRevenue(betMoney: BetMoney): BetMoney = BetMoney.ZERO
}

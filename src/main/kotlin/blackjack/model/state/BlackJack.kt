package blackjack.model.state

import blackjack.model.BetMoney
import blackjack.model.score.Score
import blackjack.model.trump.Card
import blackjack.model.trump.Cards

class BlackJack(override val cards: Cards) : State {
    override val score: Score
        get() = cards.score

    override fun add(card: Card): State {
        return this
    }

    override fun calculateRevenue(betMoney: BetMoney): BetMoney = betMoney * BLACK_JACK_TIMES

    companion object {
        private const val BLACK_JACK_TIMES = 1.5
    }
}

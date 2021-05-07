package blackjack.model.state

import blackjack.model.BetMoney
import blackjack.model.score.Score
import blackjack.model.trump.Card
import blackjack.model.trump.Cards

interface State {
    val cards: Cards
    val score: Score

    fun add(card: Card): State

    fun calculateRevenue(betMoney: BetMoney): BetMoney
}

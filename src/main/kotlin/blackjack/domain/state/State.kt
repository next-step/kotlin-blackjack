package blackjack.domain.state

import blackjack.domain.Money
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.strategy.hittable.HittableStrategy

interface State {

    val cards: Cards
    val score: Score
        get() = cards.getScore()

    fun isFinished(): Boolean
    fun draw(card: Card): State
    fun stay(): State
    fun profit(other: State, money: Money): Double
    fun canHit(hittableStrategy: HittableStrategy): Boolean
}

package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.Score

interface State {

    val cards: Cards
    val score: Score
        get() = cards.getScore()

    fun isFinished(): Boolean
    fun draw(card: Card): State
    fun match(other: State): GameResultState
    fun stay(): State
}

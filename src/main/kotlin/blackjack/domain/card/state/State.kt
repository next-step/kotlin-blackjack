package blackjack.domain.card.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Score

interface State {

    val score: Score

    val cards: Cards

    fun draw(card: Card): State

    fun stay(): State

    fun isFinished(): Boolean

    fun isHit(): Boolean
}

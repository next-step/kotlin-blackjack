package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.state.GameResultState
import blackjack.domain.state.State
import blackjack.domain.strategy.draw.DrawStrategy

abstract class Player {

    abstract val name: Name
    abstract val state: State

    val cards: Cards
        get() = state.cards

    val score: Score
        get() = state.score

    val isFinished: Boolean
        get() = state.isFinished()

    val isBust: Boolean
        get() = cards.getScore().isBust

    abstract fun draw(cardDeck: CardDeck, drawStrategy: DrawStrategy): Player
    abstract fun stay(): Player
    abstract fun canHit(): Boolean

    fun match(other: Player): GameResultState = state.match(other.state)
}

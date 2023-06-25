package next.step.blackjack.domain.card.state

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.Fightable

sealed class CardsState : Fightable<CardsState> {

    abstract fun next(cards: Cards): CardsState
}

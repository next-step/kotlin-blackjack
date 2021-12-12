package blackjack.domain.state

import blackjack.domain.card.Cards

class Blackjack(override val cards: Cards) : Finished(cards) {

    override fun match(other: State): GameResultState {
        return when (other) {
            is Blackjack -> GameResultState.Draw
            else -> GameResultState.Win
        }
    }
}

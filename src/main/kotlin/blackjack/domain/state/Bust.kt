package blackjack.domain.state

import blackjack.domain.card.Cards

class Bust(
    override val cards: Cards
) : Finished(cards) {

    override fun match(other: State): GameResultState {
        if (other is Bust) {
            return GameResultState.Draw
        }
        return GameResultState.Lose
    }
}

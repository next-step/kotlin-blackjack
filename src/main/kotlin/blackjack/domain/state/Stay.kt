package blackjack.domain.state

import blackjack.domain.card.Cards

class Stay(
    override val cards: Cards
) : Finished(cards) {

    override fun match(other: State): GameResultState {
        return when (other) {
            is Bust -> GameResultState.Win
            is Blackjack -> GameResultState.Lose
            else -> {
                GameResultState.compareStayScore(score.compareTo(other.score))
            }
        }
    }
}

package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score
import blackjack.domain.player.MatchingResult

interface State {

    val cards: Cards
    private val score: Score
        get() = cards.score

    fun isTakeable(): Boolean

    fun draw(card: Card): State

    fun stay(): Stay

    fun isBust(): Boolean

    fun match(that: State): MatchingResult {
        if (this.isBust()) {
            return MatchingResult.LOSE
        }

        if (that.isBust()) {
            return MatchingResult.WIN
        }

        return this.score.match(that.score)
    }
}

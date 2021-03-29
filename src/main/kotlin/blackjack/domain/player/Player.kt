package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Score
import blackjack.domain.state.Bust
import blackjack.domain.state.State

class Player(override val name: String, override var state: State) : Gamer {

    val score: Score
        get() = state.cards.score

    init {
        require(name.isNotBlank())
    }

    fun stay() {
        state = state.stay()
    }

    override fun isTakeable() = state.isTakeable()

    override fun takeCard(card: Card) {
        state = state.draw(card)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    fun matchResult(dealer: Dealer): MatchingResult {
        if (this.state is Bust) {
            return MatchingResult.LOSE
        }

        if (dealer.state is Bust) {
            return MatchingResult.WIN
        }

        if (this.score > dealer.score) {
            return MatchingResult.WIN
        }

        if (this.score < dealer.score) {
            return MatchingResult.LOSE
        }

        return MatchingResult.DRAW
    }
}

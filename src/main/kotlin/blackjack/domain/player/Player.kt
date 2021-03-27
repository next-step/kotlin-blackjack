package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.State

class Player(override val name: String, override var state: State) : Gamer {

    init {
        require(name.isNotBlank())
    }

    fun stay() {
        state = state.stay()
    }

    override fun isTakeable(): Boolean {
        return state is Hit
    }

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
        if (state is Bust) {
            return MatchingResult.LOSE
        }

        if (dealer.state is Bust) {
            return MatchingResult.WIN
        }

        if (state.cards.score > dealer.state.cards.score) {
            return MatchingResult.WIN
        }

        if (state.cards.score < dealer.state.cards.score) {
            return MatchingResult.LOSE
        }

        return MatchingResult.DRAW
    }
}

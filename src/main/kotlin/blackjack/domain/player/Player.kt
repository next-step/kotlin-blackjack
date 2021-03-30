package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Score
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

    fun matchResult(dealer: Dealer): MatchingResult {
        return state.match(dealer.state)
    }
}

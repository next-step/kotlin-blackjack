package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Score
import blackjack.domain.state.State

class Player(val name: String, state: State) {

    var state = state
        private set

    val score: Score
        get() = state.cards.score

    init {
        require(name.isNotBlank())
    }

    fun isTakeable() = state.isTakeable()

    fun stay() {
        state = state.stay()
    }

    fun takeCard(card: Card) {
        state = state.draw(card)
    }

    fun matchResult(dealer: Dealer): MatchingResult {
        return state.match(dealer.state)
    }

    fun stayIfNotWantDraw(inputCardTakenWhether: Boolean) {
        if (!inputCardTakenWhether) {
            stay()
        }
    }
}

package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Score
import blackjack.domain.state.State

class Dealer(name: String, state: State) {

    private val player = Player(name, state)

    val state: State
        get() = player.state

    val score: Score
        get() = player.score

    init {
        stayIfNotTakeable()
    }

    fun isTakeable() = player.isTakeable()

    fun stay() {
        player.stay()
    }

    fun takeCard(card: Card) {
        player.takeCard(card)
        stayIfNotTakeable()
    }

    private fun stayIfNotTakeable() {
        if (!isTakeable()) {
            return
        }
        if (score <= Score.DEALER_TAKEABLE_LIMIT) {
            return
        }
        stay()
    }
}

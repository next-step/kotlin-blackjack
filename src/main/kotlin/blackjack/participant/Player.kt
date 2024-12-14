package blackjack.participant

import blackjack.card.Card
import blackjack.card.Cards
import blackjack.domain.PlayerState
import blackjack.domain.PlayerState.*

data class Player(
    private val name: PlayerName,
    var cards: Cards = Cards(),
    var state: PlayerState = HIT
) : Participant(name) {
    fun take(newCards: List<Card>) {
        cards.addAll(newCards)
        score()
    }

    fun isBust(): Boolean {
        return state.isBust()
    }

    fun score(): Int {
        return cards.bestScore()
    }

    fun stand() {
        state = STAND
    }

    fun refreshState() {
        state = when {
            score() > BLACK_JACK -> BUST
            score() == BLACK_JACK -> BLACKJACK
            else -> state
        }
    }

    override fun toString(): String {
        return "${name.value}카드: $cards"
    }

    companion object {
        private const val BLACK_JACK = 21
    }
}

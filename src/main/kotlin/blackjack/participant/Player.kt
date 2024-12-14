package blackjack.participant

import blackjack.card.Card
import blackjack.card.Cards
import blackjack.domain.PlayerState

data class Player(
    private val name: PlayerName,
    var cards: Cards = Cards(),
    var state: PlayerState = PlayerState.HIT,
) : Participant(name) {
    fun take(newCards: List<Card>) {
        cards.addAll(newCards)
        refreshState()
    }

    fun isBust(): Boolean {
        return state.isBust()
    }

    fun bestScore(): Int {
        return cards.bestScore()
    }

    fun score(): Int {
        return when {
            state == PlayerState.BUST -> 0
            else -> cards.bestScore()
        }
    }

    fun stand() {
        state = PlayerState.STAND
    }

    fun refreshState() {
        state =
            when {
                bestScore() > BLACK_JACK -> PlayerState.BUST
                bestScore() == BLACK_JACK -> PlayerState.BLACKJACK
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

package blackjack.participant

import blackjack.card.DefaultCardHolder

data class Player(
    private val name: PlayerName,
    val cardHolder: DefaultCardHolder = DefaultCardHolder(),
    var state: PlayerState = PlayerState.HIT,
) : Participant(name, cardHolder) {
    fun isBust(): Boolean {
        return state.isBust()
    }

    fun getScore(): Int {
        return when {
            state == PlayerState.BUST -> 0
            else -> score()
        }
    }

    fun stand() {
        state = PlayerState.STAND
    }

    fun refreshState() {
        state =
            when {
                score() > BLACK_JACK -> PlayerState.BUST
                score() == BLACK_JACK -> PlayerState.BLACKJACK
                else -> state
            }
    }

    override fun toString(): String {
        return "${name.value}카드: ${cardHolder.cards}"
    }

    companion object {
        private const val BLACK_JACK = 21
    }
}

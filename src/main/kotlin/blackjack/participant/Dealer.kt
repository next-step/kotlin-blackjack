package blackjack.participant

import blackjack.card.DefaultCardHolder
import blackjack.participant.PlayerState.HIT

data class Dealer(
    private val name: PlayerName = PlayerName(DEALER_NAME),
    val cardHolder: DefaultCardHolder = DefaultCardHolder(),
    var state: PlayerState = HIT,
) : Participant(name, cardHolder) {
    fun isMoreCard(): Boolean {
        return score() < MORE_CARD_CONDITION_SCORE
    }

    fun stand() {
        state = PlayerState.STAND
    }

    override fun toString(): String {
        return "${name.value}카드: ${cardHolder.cards}"
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val MORE_CARD_CONDITION_SCORE = 17
    }
}

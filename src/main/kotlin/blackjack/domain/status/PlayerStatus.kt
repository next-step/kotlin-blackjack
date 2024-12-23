package blackjack.domain.status

import blackjack.domain.player.Participant

enum class PlayerStatus {
    BLACKJACK,
    BUST,
    HIT,
    STAY,
    WIN,
    LOSE,
    DRAW;

    fun checkStatus(player: Participant): PlayerStatus {
        if (player.calculateCard() == BLACKJACK_THRESHOLD && player.getAllCards().size == BLACKJACK_CONDITION) {
            return BLACKJACK
        }

        if(player.calculateCard() > BLACKJACK_THRESHOLD) {
            return BUST
        }
        return HIT
    }

    companion object {
        private const val BLACKJACK_CONDITION = 2
        private const val BLACKJACK_THRESHOLD = 21
    }
}

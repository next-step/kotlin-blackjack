package blackjack.model.status

import blackjack.model.player.BLACKJACK_MAX_NUMBER

enum class CardStatus {
    UNDER, BLACKJACK, BUST;

    companion object {
        fun getStatus(blackjackPoint: Int): CardStatus {
            if (blackjackPoint > BLACKJACK_MAX_NUMBER) {
                return BUST
            }
            if (blackjackPoint == BLACKJACK_MAX_NUMBER) {
                return BLACKJACK
            }
            return UNDER
        }
    }
}

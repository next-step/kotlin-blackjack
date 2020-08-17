package blackjack.model.status

import blackjack.model.card.PlayerCards

enum class PlayerStatus {
    PLAYING, DONE;

    companion object {
        fun getStatus(cards: PlayerCards): PlayerStatus {
            if (cards.cardStatus == CardStatus.UNDER) {
                return PLAYING
            }
            return DONE
        }
    }
}

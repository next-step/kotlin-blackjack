package blackjack.model.status

import blackjack.model.card.PlayerCards

enum class PlayerStatus {
    PLAYING, DONE;

    companion object {
        fun getStatus(cards: PlayerCards): PlayerStatus {
            if (cards.getCardStatus() == CardStatus.UNDER) {
                return PLAYING
            }
            return DONE
        }
    }
}

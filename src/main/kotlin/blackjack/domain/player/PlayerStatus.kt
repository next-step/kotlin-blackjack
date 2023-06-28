package blackjack.domain.player

import blackjack.domain.BlackjackGame
import blackjack.domain.card.Cards

enum class PlayerStatus(val isReceivable: Boolean) {
    NOT_INIT(true), RECEIVE(true), STAY(false), BUST(false), BLACK_JACK(false);

    companion object {
        const val BLACK_JACK_SCORE = 21
        fun valuesOf(cards: Cards, isReceivable: Boolean): PlayerStatus {
            val score = cards.getOptimizedScore()
            val size = cards.getCardsSize()
            if (score == BLACK_JACK_SCORE && size == BlackjackGame.INIT_CARD_COUNT) return BLACK_JACK
            if (score > BLACK_JACK_SCORE) return BUST
            if (isReceivable) return RECEIVE
            return STAY
        }
    }
}

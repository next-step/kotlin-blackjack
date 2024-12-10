package blackjack.domain

import blackjack.BlackJackGame.Companion.BLACK_JACK_DEALER_MORE_CARD_NUMBER
import blackjack.BlackJackGame.Companion.BLACK_JACK_NUMBER

data class CardScore(
    val value: Int = 0,
) {
    fun isEnabledMoreCard(isDealer: Boolean): Boolean =
        if (isDealer) {
            value < BLACK_JACK_DEALER_MORE_CARD_NUMBER
        } else {
            value <= BLACK_JACK_NUMBER
        }
}

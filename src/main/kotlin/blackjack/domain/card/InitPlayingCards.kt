package blackjack.domain.card

import blackjack.domain.model.BlackJackErrorCode

data class InitPlayingCards(private val cards: List<Card>) : PlayingCards(cards = cards.toMutableSet()) {

    init {
        check(value = size == INIT_CARD_COUNT) {
            BlackJackErrorCode.MUST_BE_SET_INIT_CARD_COUNT.message(
                arrayOf(size)
            )
        }
    }

    companion object {
        const val INIT_CARD_COUNT: Int = 2
    }
}

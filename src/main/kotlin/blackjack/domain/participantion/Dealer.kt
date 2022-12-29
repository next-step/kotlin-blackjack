package blackjack.domain.participantion

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards

class Dealer(name: String, cards: Cards) : Participant(name, cards) {
    constructor(cardDeck: CardDeck) : this(
        name = DEFAULT_NAME,
        cards = Cards(cardDeck)
    )

    fun isHittable() = cards.point() <= HIT_LIMIT_POINT

    companion object {
        private const val HIT_LIMIT_POINT = 16
        private const val DEFAULT_NAME = "딜러"
    }
}

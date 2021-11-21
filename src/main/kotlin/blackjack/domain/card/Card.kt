package blackjack.domain.card

import blackjack.domain.card.emblem.CardEmblem

data class Card(val emblem: CardEmblem, val cardNumber: Int) {
    init {
        require(cardNumber in 1..13)
    }
}

package domain.dto

import domain.card.Cards

class PlayerIssuedCardsResult(val name: String, issuedCards: Cards) {
    val cards: Cards

    init {
        cards = Cards(issuedCards)
    }
}

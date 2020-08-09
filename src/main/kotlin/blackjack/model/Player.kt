package blackjack.model

import blackjack.model.card.Card

data class Player(val name: String) {
    var cards = mutableListOf<Card>()
}

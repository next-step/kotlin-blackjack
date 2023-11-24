package blackjack.domain.cards

import blackjack.domain.card.Card

data class HandCards(private val _cardList: MutableList<Card>) : Cards(_cardList) {
    init {
        require(_cardList.size == 2) { "Hand should be initialized with 2 cards" }
    }
}

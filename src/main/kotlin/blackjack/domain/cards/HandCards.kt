package blackjack.domain.cards

import blackjack.domain.card.Card

data class HandCards(private val _cardList: MutableList<Card>) : Cards(_cardList)

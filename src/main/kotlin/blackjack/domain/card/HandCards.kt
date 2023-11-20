package blackjack.domain.card

import blackjack.model.Card

data class HandCards(private val _cardList: MutableList<Card>) : Cards(_cardList)

package blackjack.model.cardset

import blackjack.model.card.Card

data class Cards(private val cardList: List<Card> = listOf()) : List<Card> by cardList

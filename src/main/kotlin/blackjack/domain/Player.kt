package blackjack.domain

import blackjack.domain.card.Card

data class Player(val name: String, val cards: MutableList<Card> = mutableListOf(), var score: Int = 0)

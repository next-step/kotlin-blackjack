package blackjack.domain

data class Cards(val cards: MutableList<Card>) : MutableList<Card> by cards

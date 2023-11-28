package blackjack.domain

data class Cards(val cards: MutableSet<Card>) : MutableSet<Card> by cards

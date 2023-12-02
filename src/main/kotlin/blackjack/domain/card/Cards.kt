package blackjack.domain.card

data class Cards(val cards: MutableSet<Card> = mutableSetOf()) : MutableSet<Card> by cards

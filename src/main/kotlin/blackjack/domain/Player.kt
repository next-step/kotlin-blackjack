package blackjack.domain

data class Player(override val name: String, override val cards: Cards = Cards()) : Participant(name, cards)

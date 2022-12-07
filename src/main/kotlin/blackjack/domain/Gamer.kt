package blackjack.domain

data class Gamer(override val name: String, override val cards: Cards = Cards()) : Player(name, cards)

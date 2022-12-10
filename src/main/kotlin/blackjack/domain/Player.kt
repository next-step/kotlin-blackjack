package blackjack.domain

data class Player(
    override val name: String,
    override val myCards: Cards = Cards()
) : Participant(name, myCards)

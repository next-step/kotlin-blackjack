package blackjack.domain

import blackjack.model.Card

interface Player {
    val name: String

    val play: Play
}

interface Dealer {
    val deck: CardDeck
    val name: String
    val play: Play
    fun deliverCard(): Card
    fun shuffle()
}

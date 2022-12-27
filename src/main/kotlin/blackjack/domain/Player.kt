package blackjack.domain

import blackjack.model.Bet
import blackjack.model.Card

interface Player {
    val name: String

    val play: Play

    val bet: Bet
}

interface Dealer {
    val deck: CardDeck
    val name: String
    val play: Play
    fun deliverCard(): Card
    fun shuffle()
}

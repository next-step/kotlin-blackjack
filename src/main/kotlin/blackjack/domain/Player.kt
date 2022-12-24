package blackjack.domain

import blackjack.model.Card

interface Player {
    val name: String
    val cards: Cards
    val state: State
    val finished: Boolean

    fun shouldBeReadyToPlay(): Boolean
    fun draw(card: Card)
    fun stay()
    fun sumCards(): Int
}

interface Dealer {
    val deck: CardDeck
    val name: String
    val cards: Cards
    val state: State
    val finished: Boolean
    fun deliverCard(): Card
    fun shouldStay(): Boolean
    fun shouldBeReadyToPlay(): Boolean
    fun shuffle()

    fun draw(card: Card)
    fun stay()
    fun sumCards(): Int
}

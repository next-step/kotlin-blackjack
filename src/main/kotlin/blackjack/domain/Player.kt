package blackjack.domain

import blackjack.model.Card

interface Player {
    val name: String

    val cards: Cards
    fun readyToPlay(initialCards: List<Card>)
    fun hit(card: Card): Boolean
    fun sumCards(): Int
    fun bust(): Boolean
    fun blackjack(): Boolean
}

interface Dealer: Player {
    val deck: CardDeck
    fun shuffle()
    fun deliverCard(): Card
    fun stay(): Boolean
}

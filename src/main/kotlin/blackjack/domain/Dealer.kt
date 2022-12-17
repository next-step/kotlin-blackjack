package blackjack.domain

import blackjack.model.Card

class Dealer(
    private val _deck: CardDeck = CardDeckImpl(),
    val cards: Cards = Cards(),
) : GamePlay {
    val deck: CardDeck
        get() = _deck

    fun shuffle() = _deck.shuffle()

    fun deliverCard(): Card = _deck.takeOutFirstCard()
    override fun readyToPlay(initialCards: List<Card>) {
        TODO("Not yet implemented")
    }

    override fun hit(card: Card): Boolean {
        TODO("Not yet implemented")
    }

    override fun sumCards(): Int {
        TODO("Not yet implemented")
    }

    override fun burst(): Boolean {
        TODO("Not yet implemented")
    }

    override fun blackjack(): Boolean {
        TODO("Not yet implemented")
    }
}

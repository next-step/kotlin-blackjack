package blackjack.domain

class Dealer {

    private val cards: Cards = Cards()

    fun getCard(): Card = cards.getCard()
}

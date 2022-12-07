package blackjack.domain

class Dealer {

    private val cards: DealerCards = DealerCards()

    fun getCard(): Card = cards.getCard()
}

package blackjack.domain

data class Dealer(override val name: String = "dealer", override val cards: Cards = Cards()) : Player(name, cards) {

    private val dealerCards: DealerCards = DealerCards()

    fun extractCard(): Card = dealerCards.getCard()
}

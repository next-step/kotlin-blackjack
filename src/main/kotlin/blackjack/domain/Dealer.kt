package blackjack.domain

data class Dealer(
    override val name: String = "dealer",
    override val cards: Cards = Cards()
) : Participant(name, cards) {

    private val dealerCards: DealerCards = DealerCards()

    override fun toString(): String {
        return "${name}카드: $cards"
    }

    fun extractCard(): Card = dealerCards.getCard()
}

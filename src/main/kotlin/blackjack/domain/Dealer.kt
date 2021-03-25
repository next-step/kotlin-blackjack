package blackjack.domain

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards(arrayListOf())
) : GameParticipants(name, cards) {

    override fun drawCard() {
        if (checkCardsSumIsOver()) {
            cards.drawCard()
        }
    }

    override fun calculateMyCards(): Int {
        return cards.calculateMyCards()
    }

    private fun checkCardsSumIsOver(): Boolean = calculateMyCards() < DEALER_CARDS_STANDARD

    companion object {
        private const val DEALER_CARDS_STANDARD = 17
    }
}

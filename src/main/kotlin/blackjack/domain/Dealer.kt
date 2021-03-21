package blackjack.domain

class Dealer(
    name: String = "딜러"
) : GameParticipants(name) {

    override fun drawCard() {
        if(checkCardsSumIsOver()) {
            cards.drawCard()
        }
    }

    override fun calculateMyCards(): Int {
        return cards.calculateMyCards()
    }

    private fun checkCardsSumIsOver(): Boolean {
        if(calculateMyCards() >= 17)  return false
        return true
    }

    companion object {
        private const val DEALER_CARDS_STANDARD = 17
    }
}

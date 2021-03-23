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

    private fun checkCardsSumIsOver(): Boolean {
        if (calculateMyCards() >= 17) {
            println("딜러는 17이상이라 카드를 받지 않습니다.")
            return false
        }
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        return true
    }

    companion object {
        private const val DEALER_CARDS_STANDARD = 17
    }
}

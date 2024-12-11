package blackjack.domain

class Dealer(private val deck: Deck, bet: Int) : Player("딜러", bet) {
    fun initialDraw() {
        addCards(deck.drawCards(2))
    }

    fun shouldDrawCard(): Boolean {
        return score <= 16
    }

    fun drawCard() {
        addCards(deck.drawCards(1))
    }

    override fun canContinue(): Boolean {
        return score <= 21
    }
}

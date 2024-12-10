package blackjack.domain

class Dealer(private val deck: Deck) : Player("딜러") {
    fun initialDraw() {
        addCards(deck.drawCards(2))
    }

    fun shouldDrawCard(): Boolean {
        return score <= 16
    }

    fun drawCard() {
        addCards(deck.drawCards(1))
    }
}

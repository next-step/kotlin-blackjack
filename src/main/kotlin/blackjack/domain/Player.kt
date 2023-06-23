package blackjack.domain

class Player(val name: String) {

    private val hand: Hand = Hand()
    var ableToDraw = true

    fun hit(vararg pokerCards: PokerCard) {
        check(ableToDraw) { "더이상 카드를 받을 수 없습니다." }

        for (pokerCard in pokerCards) {
            hand.addNewCard(pokerCard)
        }
        val blackJackOrBust = hand.isBlackJackOrBust()
        if (blackJackOrBust) ableToDraw = false
    }

    fun hands(): List<PokerCard> {
        return hand.hands()
    }

    fun stand() {
        this.ableToDraw = false
    }

    fun optimalValue(): Int {
        return hand.optimalValue()
    }

    fun showHands(): String {
        return hand.toRepresent()
    }
}

package blackjack.domain

import blackjack.HandsCalculator

class Player(val name: String) {

    private val hand = mutableListOf<PokerCard>()
    var ableToDraw = true

    fun receiveCard(vararg pokerCards: PokerCard) {
        require(ableToDraw) { "더이상 카드를 받을 수 없습니다." }

        for (pokerCard in pokerCards) {
            hand.add(pokerCard)
        }
        val blackJackOrBust = HandsCalculator.calculateOptimalValue(hand) >= 21
        if (blackJackOrBust) ableToDraw = false
    }

    fun hands(): List<PokerCard> {
        return hand.toList()
    }

    fun performAction(wantToHit: Boolean, dealer: Dealer) {
        if (wantToHit) {
            receiveCard(dealer.draw())
        } else {
            this.ableToDraw = false
        }
    }

    fun optimalValue(): Int {
        return HandsCalculator.calculateOptimalValue(hand)
    }

    fun showHands(): String {
        return hand.joinToString(transform = PokerCard::representCard)
    }
}

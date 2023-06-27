package blackjack.common.domain

import blackjack.common.service.DeckManager

abstract class Player<T : Player<T>>(val name: String) {

    private val hand: Hand = Hand()

    fun hit(vararg pokerCards: PokerCard) {
        check(hand.isNotBlackJackOrNotBust()) { "더이상 카드를 받을 수 없습니다." }

        for (pokerCard in pokerCards) {
            hand.addNewCard(pokerCard)
        }
    }

    fun hands(): List<PokerCard> {
        return hand.hands()
    }

    fun optimalValue(): Int {
        return hand.optimalValue()
    }

    fun showHands(): String {
        return hand.toRepresent()
    }

    abstract fun drawPhase(
        deckManager: DeckManager,
        handNotice: (T) -> Unit
    )

    abstract fun canDraw(): Boolean
}

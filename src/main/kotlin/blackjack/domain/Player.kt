package blackjack.domain

import blackjack.service.DeckManager

open class Player(val name: String) {

    private val hand: Hand = Hand()
    private val scoreBoard = ScoreBoard()

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

    fun scoreBoard(): ScoreBoard {
        return this.scoreBoard
    }

    fun drawPhase(
        deckManager: DeckManager,
        handNotice: (Player) -> Unit
    ) {
        hit(deckManager.draw())
        handNotice.invoke(this)
    }
}

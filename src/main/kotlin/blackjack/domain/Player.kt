package blackjack.domain

import blackjack.DeckManager

open class Player(val name: String) {

    private val hand: Hand = Hand()
    private val scoreBoard = ScoreBoard()

    fun hit(vararg pokerCards: PokerCard) {
        check(hand.ableToDraw()) { "더이상 카드를 받을 수 없습니다." }

        for (pokerCard in pokerCards) {
            hand.addNewCard(pokerCard)
        }
        hand.changeAbleToDraw(!hand.isBlackJackOrBust())
    }

    fun hands(): List<PokerCard> {
        return hand.hands()
    }

    fun ableToDraw(): Boolean {
        return hand.ableToDraw()
    }

    fun stand() {
        hand.changeAbleToDraw(false)
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

    open fun gameResult(): String {
        return this.scoreBoard.resultForPlayer()
    }

    fun drawPhase(
        wantToHit: Boolean = hand.ableToDraw(),
        deckManager: DeckManager,
        handNotice: (Player) -> Unit
    ) {
        if (wantToHit) {
            hit(deckManager.draw())
            handNotice.invoke(this)
        } else {
            stand()
        }
    }
}

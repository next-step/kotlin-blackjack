package blackjack.domain

import blackjack.DeckManager

open class Player(val name: String) {

    private val hand: Hand = Hand()
    private val scoreBoard = ScoreBoard()
    private var wantToDraw = true

    fun hit(vararg pokerCards: PokerCard) {
        check(wantToDraw) { "더이상 카드를 받을 수 없습니다." }

        for (pokerCard in pokerCards) {
            hand.addNewCard(pokerCard)
        }

        wantToDraw = hand.isNotBlackJackOrNotBust()
    }

    fun hands(): List<PokerCard> {
        return hand.hands()
    }

    fun stand() {
        this.wantToDraw = false
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

    fun wantToDraw(): Boolean {
        return wantToDraw
    }

    fun drawPhase(
        wantToHit: Boolean = wantToDraw,
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

package blackjack.scorerule.domain

import blackjack.common.domain.Player
import blackjack.common.service.DeckManager

open class ScorePlayer(name: String) : Player<ScorePlayer>(name) {

    private val scoreBoard = ScoreBoard()

    fun scoreBoard(): ScoreBoard {
        return this.scoreBoard
    }

    override fun drawPhase(deckManager: DeckManager, handNotice: (ScorePlayer) -> Unit) {
        hit(deckManager.draw())
        handNotice.invoke(this)
    }

    override fun canDraw(): Boolean {
        return true
    }
}

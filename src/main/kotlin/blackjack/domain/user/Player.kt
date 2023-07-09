package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.status.*

open class Player(val name: String, val cards: Cards = Cards()) {
    var status: Status = Hit(this)

    fun chooseHitOrStay(isPlayerWantHit: Boolean, dealer: Dealer) {
        if (isPlayerWantHit) {
            draw(dealer.deck.getNextCard())
            return
        }
        status = status.stay()
    }

    open fun draw(card: Card, count: Int = 1) {
        repeat(count) { status = status.draw(card) }
    }

    open fun getGameResult(): GameResult {
        if (status is Win) {
            return GameResult(1, 0, 0)
        }
        if (status is Lose) {
            return GameResult(0, 1, 0)
        }
        return GameResult(0, 0, 1)
    }

    data class GameResult(val winCount: Int, val loseCount: Int, val drawCount: Int)

    fun isDone(): Boolean = status is EndStatus
}

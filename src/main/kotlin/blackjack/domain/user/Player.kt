package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
import blackjack.domain.status.*

open class Player(val name: String, val cards: Cards = Cards()) {
    private var _status: Status = Hit(this)
    var status: Status = _status
        get() = _status


    fun chooseHitOrStay(isPlayerWantHit: Boolean, deck: Deck) {
        if (isPlayerWantHit) {
            draw(deck.getNextCard())
            return
        }
        stay()
    }

    fun stay() {
        _status = _status.stay()
    }

    fun calculateResult(dealer: Dealer): Status {
        _status = _status.calculateResult(dealer)
        return _status
    }

    open fun draw(card: Card, count: Int = 1) {
        repeat(count) { _status = _status.draw(card) }
    }

    open fun getGameResult(): GameResult {
        if (_status is Win) {
            return GameResult(1, 0, 0)
        }
        if (_status is Lose) {
            return GameResult(0, 1, 0)
        }
        return GameResult(0, 0, 1)
    }

    data class GameResult(val winCount: Int, val loseCount: Int, val drawCount: Int)
}

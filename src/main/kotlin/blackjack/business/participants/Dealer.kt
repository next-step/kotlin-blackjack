package blackjack.business.participants

import blackjack.business.canDrawCardStrategy.DealerCanDrawCardStrategy
import blackjack.business.card.Card
import blackjack.business.util.GameResult

class Dealer {
    private val _dealerCards: PlayerCards = PlayerCards(canDrawCardStrategy = DealerCanDrawCardStrategy())
    val cards: List<Card>
        get() = _dealerCards.cards

    fun addCard(card: Card) {
        _dealerCards.add(card)
    }

    fun canDrawCard(): Boolean {
        return _dealerCards.canDrawCard()
    }

    fun isBust(): Boolean {
        return _dealerCards.isBust()
    }

    fun getResult(target: Int): GameResult {
        if (isBust()) {
            return GameResult.WIN
        }
        return when (target - _dealerCards.sum()) {
            0 -> GameResult.DRAW
            in 1..Int.MAX_VALUE -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }
}

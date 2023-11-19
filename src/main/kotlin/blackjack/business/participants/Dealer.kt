package blackjack.business.participants

import blackjack.business.card.Card
import blackjack.business.card.CardDesk
import blackjack.business.util.GameResult

class Dealer(dealerCards: PlayerCards = PlayerCards()) {
    private val _dealerCards: PlayerCards = dealerCards
    val cards: List<Card>
        get() = _dealerCards.cards
    val score: String
        get() = _dealerCards.sum().toString()

    fun addCard(card: Card) {
        _dealerCards.add(card)
    }

    fun addCards(cards: List<Card>) {
        _dealerCards.addAll(cards)
    }

    fun canDrawCard(): Boolean {
        return _dealerCards.sum() < 17
    }

    fun isBust(): Boolean {
        return _dealerCards.isBust()
    }

    fun getPlayerResult(player: Player): PlayerResult {
        if (isBust()) {
            return PlayerResult(player.name, GameResult.WIN)
        }
        if (player.isBust()) {
            return PlayerResult(player.name, GameResult.LOSE)
        }
        return when (player.score - _dealerCards.sum()) {
            0 -> PlayerResult(player.name, GameResult.DRAW)
            in 1..Int.MAX_VALUE -> PlayerResult(player.name, GameResult.WIN)
            else -> PlayerResult(player.name, GameResult.LOSE)
        }
    }

    fun getDealerResult(target: Players): Map<GameResult, Int> {
        return target.allPlayers.map { getDealerResult(it.score) }.groupingBy { it }.eachCount()
    }

    fun executeCardDraws(cardDesk: CardDesk, announcer: () -> Unit) {
        if (canDrawCard()) {
            announcer()
            addCard(cardDesk.draw())
        }
    }

    private fun getDealerResult(target: Int): GameResult {
        if (isBust()) {
            return GameResult.WIN
        }
        if (target > 21) {
            return GameResult.WIN
        }
        return when (target - _dealerCards.sum()) {
            0 -> GameResult.DRAW
            in 1..Int.MAX_VALUE -> GameResult.LOSE
            else -> GameResult.WIN
        }
    }
}

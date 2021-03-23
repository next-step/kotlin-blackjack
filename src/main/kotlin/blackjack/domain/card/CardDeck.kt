package blackjack.domain.card

import util.toStack
import java.util.Stack

class CardDeck(private val shuffleStrategy: (List<Card>) -> List<Card>) {
    private var _cards: Stack<Card> = initCard()
    val cards
        get() = _cards.toList()

    private fun initCard(): Stack<Card> {
        val deck = CardSymbol.values().flatMap { symbol -> CardSuit.values().map { Card(symbol, it) } }
        return shuffleStrategy(deck).toStack()
    }

    fun pop(): Card {
        if (_cards.isEmpty()) {
            _cards = initCard()
            return _cards.pop()
        }
        return _cards.pop()
    }
}

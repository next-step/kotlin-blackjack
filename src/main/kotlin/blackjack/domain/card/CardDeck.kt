package blackjack.domain.card

import util.toStack
import java.util.Stack

class CardDeck {
    private val _cards: Stack<Card> = initCard()

    private fun initCard() =
        CardSymbol.values().flatMap { symbol -> CardSuit.values().map { Card(symbol, it) } }
            .shuffled()
            .toStack()

    fun pop(): Card {
        return _cards.pop()
    }
}

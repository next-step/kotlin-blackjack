package blackjack.common.domain

import blackjack.common.domain.builder.pokerCardOf

class Deck {
    private val deck: MutableList<PokerCard> = List(DECK_SIZE) { index ->
        val symbolValue = index / SYMBOL_COUNT
        val cardValue = index % SYMBOL_COUNT + ADJUSTED_INDEX
        val pokerSymbol = PokerSymbol.getSymbolByDrawNumber(symbolValue)
        pokerCardOf {
            symbol(pokerSymbol)
            value(cardValue)
            rank(cardValue)
        }
    }.shuffled().toMutableList()

    fun drawCard(): PokerCard {
        check(deck.isNotEmpty()) { EMPTY_DECK_ERROR }
        return deck.removeFirst()
    }

    companion object {
        private const val ADJUSTED_INDEX = 1
        private const val SYMBOL_COUNT = 13
        private const val DECK_SIZE = 52
        private const val EMPTY_DECK_ERROR = "모든 덱이 소진되었습니다."
    }
}

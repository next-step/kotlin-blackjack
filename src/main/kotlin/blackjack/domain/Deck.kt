package blackjack.domain

import blackjack.drawNewCard
import kotlin.random.Random

class Deck {

    private val graveyard = Array(DECK_SIZE) { false }
    private val random = Random

    fun drawCard(): PokerCard {
        var drawIndex: Int
        do {
            drawIndex = random.nextInt(DECK_SIZE)
        } while (graveyard[drawIndex])

        graveyard[drawIndex] = true
        val pokerSymbol = PokerSymbol.getSymbolByDrawNumber(drawIndex)
        val cardValue = (drawIndex + REALIZATION_NUMBER) % SYMBOL_COUNT

        return drawNewCard {
            symbol(pokerSymbol)
            value(cardValue)
            rank(cardValue)
        }
    }

    fun canDrawFromDeck(n: Int): Boolean {
        return graveyard.filter { !it }.size >= n
    }

    companion object {
        private const val DECK_SIZE = 52
        private const val SYMBOL_COUNT = 13
        private const val REALIZATION_NUMBER = 1
    }
}

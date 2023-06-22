package blackjack.domain

import blackjack.draw
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

        return draw {
            symbol(pokerSymbol)
            value(drawIndex)
        }
    }

    companion object {
        private const val DECK_SIZE = 52
    }
}

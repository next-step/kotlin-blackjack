package blackjack.domain.builder

import blackjack.domain.PokerCard
import blackjack.domain.PokerSymbol

class PokerCardBuilder {

    private lateinit var symbol: PokerSymbol
    private var value: Int = 0

    fun symbol(symbol: PokerSymbol) {
        this.symbol = symbol
    }

    fun value(drawIndex: Int) {
        this.value = drawIndex + REALIZATION_NUMBER
    }

    fun build(): PokerCard {
        return PokerCard(symbol, value)
    }

    companion object {
        private const val REALIZATION_NUMBER = 1
    }
}

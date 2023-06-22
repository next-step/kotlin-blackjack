package blackjack.domain.builder

import blackjack.domain.PokerCard
import blackjack.domain.PokerSymbol

class PokerCardBuilder {

    private lateinit var symbol: PokerSymbol
    private var value: Int = 0
    private var isAce = false

    fun symbol(symbol: PokerSymbol) {
        this.symbol = symbol
    }

    fun value(cardValue: Int) {
        this.value = when {
            cardValue == KING_NUMBER -> FACE_CARD_VALUE
            cardValue == ACE_NUMBER -> ACE_VALUE
            cardValue > FACE_CARD_VALUE -> FACE_CARD_VALUE
            else -> cardValue
        }
    }

    fun isAce(cardValue: Int) {
        this.isAce = cardValue == ACE_NUMBER

    }
    fun build(): PokerCard {
        return PokerCard(symbol, value, isAce)
    }

    companion object {
        private const val ACE_NUMBER = 1
        private const val ACE_VALUE = 11
        private const val FACE_CARD_VALUE = 10
        private const val KING_NUMBER = 0
    }
}

package blackjack.common.domain.builder

import blackjack.common.domain.PokerCard
import blackjack.common.domain.PokerSymbol

class PokerCardBuilder {

    private lateinit var symbol: PokerSymbol
    private var value: Int = 0
    private lateinit var rank: String

    fun symbol(symbol: PokerSymbol) {
        this.symbol = symbol
    }

    fun value(cardValue: Int) {
        this.value = when {
            cardValue == ACE_NUMBER -> ACE_VALUE
            cardValue > FACE_CARD_VALUE -> FACE_CARD_VALUE
            else -> cardValue
        }
    }

    fun rank(cardValue: Int) {
        this.rank = when (cardValue) {
            ACE_NUMBER -> "A"
            JACK_NUMBER -> "J"
            QUEEN_NUMBER -> "Q"
            KING_NUMBER -> "K"
            else -> cardValue.toString()
        }
    }

    fun build(): PokerCard {
        return PokerCard(symbol, value, rank)
    }

    companion object {
        private const val ACE_NUMBER = 1
        private const val ACE_VALUE = 11
        private const val FACE_CARD_VALUE = 10
        private const val JACK_NUMBER = 11
        private const val QUEEN_NUMBER = 12
        private const val KING_NUMBER = 13
    }
}

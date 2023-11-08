package blackjack.domain

import blackjack.dsl.BlackJackDsl

@JvmInline
value class TrumpCard(val cards: List<Card> = emptyList()) {
    init {
        require(cards.size == CARD_COUNT) { ERROR_MESSAGE }
    }

    companion object {
        private const val CARD_COUNT = 52
        private const val ERROR_MESSAGE = "카드는 52장이어야 합니다."

        fun init(): TrumpCard {
            return BlackJackDsl.initTrumpCard {
                for (suit in Suit.values()) {
                    cards(Rank.values().toList().map { Card(suit, it) })
                }
                shuffle()
            }
        }
    }
}

package blackjack.domain

import blackjack.dsl.BlackJackDsl

@JvmInline
value class TrumpCard(val cards: MutableList<Card> = mutableListOf()) {
    fun draw(): Card {
        return cards.removeFirst()
    }

    companion object {

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

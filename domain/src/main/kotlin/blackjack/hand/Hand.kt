package blackjack.hand

import blackjack.card.Card

interface Hand {
    fun cards(): List<Card>
    fun addCard(card: Card): Hand
    fun addCard(cards: List<Card>): Hand
    fun calculateMinValue(): Int
    fun calculateBestValue(): Int

    companion object {
        const val FACE_CARD_VALUE = 10
        const val MAX_HAND_VALUE = 21
        const val ACE_HIGH_VALUE = 11
        const val ACE_LOW_VALUE = 1
    }
}

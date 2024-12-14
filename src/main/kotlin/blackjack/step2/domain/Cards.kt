package blackjack.step2.domain

import blackjack.step2.domain.GameInitializer.Companion.INITIAL_DEAL_COUNT

data class Cards private constructor(private val cards: List<Card>) {
    val all: List<Card> get() = cards

    fun add(card: Card): Cards = Cards(cards + card)

    fun totalScore(): Int {
        return cards.fold(0) { totalScore, card ->
            val currentScore = totalScore + card.number.score
            totalScore + card.number.calculateScore(currentScore)
        }
    }

    fun isInitialBlackjack(): Boolean {
        if (cards.size != INITIAL_DEAL_COUNT) return false
        return this.totalScore() == Participant.BLACKJACK_SCORE
    }

    companion object {
        fun of(cards: List<Card>): Cards = Cards(cards)
    }
}

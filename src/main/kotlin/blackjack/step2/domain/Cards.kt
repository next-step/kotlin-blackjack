package blackjack.step2.domain

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
        if (cards.size != 2) return false
        return this.totalScore() == Participant.BLACKJACK_SCORE
    }

    companion object {
        fun of(cards: List<Card>): Cards = Cards(cards)
    }
}

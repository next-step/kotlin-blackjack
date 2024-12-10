package blackjack.step2.domain

class Cards private constructor(private val cards: List<Card>) {
    val all: List<Card> get() = cards

    fun add(card: Card): Cards = Cards(cards + card)

    fun totalScore(): Int {
        return cards.fold(0) { totalScore, card ->
            val currentScore = totalScore + card.number.score
            totalScore + card.number.calculateScore(currentScore)
        }
    }

    companion object {
        fun of(cards: List<Card>): Cards = Cards(cards)
    }
}

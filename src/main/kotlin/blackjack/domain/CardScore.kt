package blackjack.domain

class CardScore(val scores: List<Score>) {
    companion object {
        fun from(value: Int): CardScore {
            return CardScore(listOf(Score(value)))
        }
    }
}
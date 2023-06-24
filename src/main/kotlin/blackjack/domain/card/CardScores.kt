package blackjack.domain.card

class CardScores(val value: List<CardScore>) {
    companion object {
        fun from(value: Int): CardScores {
            return CardScores(listOf(CardScore(value)))
        }
    }
}

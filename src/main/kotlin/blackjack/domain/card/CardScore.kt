package blackjack.domain.card

data class CardScore(val score: Int) {
    operator fun plus(other: CardScore): CardScore {
        return CardScore(score + other.score)
    }
}

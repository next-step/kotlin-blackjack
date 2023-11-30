package blackjack.domain.card

interface CardScorePolicy {
    fun getScore(card: Card): CardScore
}

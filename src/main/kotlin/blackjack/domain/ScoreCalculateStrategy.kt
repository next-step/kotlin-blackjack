package blackjack.domain

interface ScoreCalculateStrategy {

    fun calculate(cards: List<Card>): Score
}

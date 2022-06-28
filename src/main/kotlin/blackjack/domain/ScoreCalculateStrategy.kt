package blackjack.domain

import blackjack.vo.Score

interface ScoreCalculateStrategy {

    fun calculate(cards: List<Card>): Score
}

package blackjack.domain

import blackjack.vo.Score

interface ScoreCalculateStrategy {

    fun calculate(hand: Hand): Score
}

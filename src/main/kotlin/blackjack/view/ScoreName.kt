package blackjack.view

import blackjack.domain.score.ScoreType

object ScoreName {
    fun of(score: ScoreType) = when (score) {
        ScoreType.WIN -> "승"
        ScoreType.DRAW -> "무"
        ScoreType.LOSE -> "패"
    }
}

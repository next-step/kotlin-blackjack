package blackjack.domain

class Score(val score: Int) {

    companion object {
        fun from(score: Int): Score = Score(score)
    }
}
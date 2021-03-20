package blackjack.model

import java.util.TreeSet

class Scores private constructor(private val scores: TreeSet<Score>) : Set<Score> by scores {
    fun lowest(): Score {
        return scores.first()
    }

    fun highest(): Score {
        return scores.last()
    }

    class Builder {
        private lateinit var scores: List<Score>

        fun scores(scores: List<Score>): Builder {
            this.scores = scores
            return this
        }

        fun scoresFromInt(scores: List<Int>): Builder {
            this.scores = scores.map { Score(it) }
            return this
        }

        fun build(): Scores {
            return Scores(TreeSet(scores))
        }
    }
}

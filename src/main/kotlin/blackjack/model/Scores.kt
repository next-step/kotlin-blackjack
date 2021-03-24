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
        private var scores: List<Score> = listOf()

        fun scores(scores: List<Score>): Builder {
            this.scores = scores
            return this
        }

        fun build(): Scores {
            return Scores(TreeSet(scores))
        }
    }
}

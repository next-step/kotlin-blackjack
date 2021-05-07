package blackjack.model.score

import java.util.TreeSet

class Scores private constructor(private val scores: TreeSet<Score>) {

    val size: Int
        get() = scores.size

    constructor(scores: List<Int>) : this(TreeSet(scores.map { Score(it) }))

    constructor(vararg scores: Int) : this(scores.toList())

    fun lowest(): Score {
        return scores.first()
    }

    fun highest(): Score {
        return scores.last()
    }
}

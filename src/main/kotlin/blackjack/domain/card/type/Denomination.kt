package blackjack.domain.card.type

import blackjack.domain.Score

sealed class Denomination(
    val description: String,
    val score: Score
) : Comparable<Denomination> {
    override fun compareTo(other: Denomination): Int = score.value - other.score.value
}

class Ace : Denomination("A", Score(1)) {
    val aceScore: Score
        get() = Score(11)
}

class Two : Denomination("2", Score(2))

class Three : Denomination("3", Score(3))

class Four : Denomination("4", Score(4))

class Five : Denomination("5", Score(5))

class Six : Denomination("6", Score(6))

class Seven : Denomination("7", Score(7))

class Eight : Denomination("8", Score(8))

class Nine : Denomination("9", Score(9))

class Ten : Denomination("10", Score(10))

class Jack : Denomination("J", Score(10))

class Queen : Denomination("Q", Score(10))

class King : Denomination("K", Score(10))

package blackjack.domain.card.type

import blackjack.domain.Score

sealed class Denomination(
    val description: String,
    val score: Score
) : Comparable<Denomination> {
    override fun compareTo(other: Denomination): Int = score.value - other.score.value
}

class Ace : Denomination("A", Score.of(1)) {
    val aceScore: Score
        get() = Score.of(11)
}

class Two : Denomination("2", Score.of(2))

class Three : Denomination("3", Score.of(3))

class Four : Denomination("4", Score.of(4))

class Five : Denomination("5", Score.of(5))

class Six : Denomination("6", Score.of(6))

class Seven : Denomination("7", Score.of(7))

class Eight : Denomination("8", Score.of(8))

class Nine : Denomination("9", Score.of(9))

class Ten : Denomination("10", Score.of(10))

class Jack : Denomination("J", Score.of(10))

class Queen : Denomination("Q", Score.of(10))

class King : Denomination("K", Score(10))

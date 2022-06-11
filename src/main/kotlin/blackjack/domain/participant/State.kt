package blackjack.domain.participant

import blackjack.domain.card.Point

sealed class State {
    abstract fun compare(other: State): Match
}

object Hittable : State() {
    override fun compare(other: State): Match {
        throw IllegalStateException("Hittable 상태는 다른 상태와 비교될 수 없습니다")
    }
}

object BlackJack : State() {
    override fun compare(other: State): Match {
        return when (other) {
            is BlackJack -> Match.DRAW
            else -> Match.WIN
        }
    }
}

data class Stay(
    val point: Point
) : State() {
    override fun compare(other: State): Match {
        return when (other) {
            is BlackJack -> Match.LOSE
            is Stay -> comparePoint(other.point)
            else -> Match.WIN
        }
    }

    private fun comparePoint(point: Point): Match {
        return when {
            this.point > point -> Match.WIN
            this.point == point -> Match.DRAW
            else -> Match.LOSE
        }
    }
}

object Bust : State() {
    override fun compare(other: State): Match {
        return when (other) {
            is Bust -> Match.DRAW
            else -> Match.LOSE
        }
    }
}

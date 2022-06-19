package blackjack.domain.participant

import blackjack.domain.card.Point

sealed interface State {
    fun compare(other: State): Match
}

object Hittable : State {
    override fun compare(other: State): Match {
        if (other is BlackJack) return Match.LOSE
        throw IllegalStateException("Hittable 상태는 BlackJack을 제외한 다른 상태와 비교할 수 없습니다")
    }
}

object BlackJack : State {
    override fun compare(other: State): Match {
        return when (other) {
            BlackJack -> Match.DRAW
            is Stay -> Match.WIN
            Bust -> Match.WIN
            Hittable -> Match.WIN
        }
    }
}

data class Stay(
    val point: Point
) : State {
    override fun compare(other: State): Match {
        return when (other) {
            BlackJack -> Match.LOSE
            is Stay -> comparePoint(other.point)
            Bust -> Match.WIN
            Hittable -> Match.WIN
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

object Bust : State {
    override fun compare(other: State): Match {
        return when (other) {
            Bust -> Match.DRAW
            BlackJack -> Match.LOSE
            is Stay -> Match.LOSE
            Hittable -> Match.LOSE
        }
    }
}

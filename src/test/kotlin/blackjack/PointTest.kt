package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointTest {

    @Test
    fun `21이 넘지 않으면서 두 장중 첫 장이 ACE 일 때 추가 점수 획득`() {
        val isAvailableExtraPoint =
            Point.isAvailableExtraPoint(Point(Denomination.ACE.point), Point(Denomination.TEN.point))

        assertThat(isAvailableExtraPoint).isTrue()
    }
}

class Point(val value: Int) {

    operator fun plus(point: Point): Point = Point(value + point.value)

    operator fun compareTo(point: Point) =
        when {
            value > point.value -> 1
            value == point.value -> 0
            else -> -1
        }

    companion object {
        private val MAX_POINT = Point(21)
        private val EXTRA_ACE_POINT = Point(10)

        fun isAvailableExtraPoint(acc: Point, point: Point): Boolean {
            val isAccAce = Denomination.isAce(acc.value)
            val notExceedMaxPoint = acc + point + EXTRA_ACE_POINT <= MAX_POINT
            return isAccAce && notExceedMaxPoint
        }
    }
}

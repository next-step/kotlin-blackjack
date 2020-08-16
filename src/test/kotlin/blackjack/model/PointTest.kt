package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointTest {

    @Test
    fun `첫 장이 ACE 일 때 ACE 추가 점수 획득`() {
        val pointIfAceFirst = Point.calculateIfAceFirst(
            Point(Denomination.ACE.point)
        )

        assertThat(pointIfAceFirst).isEqualTo(Point(11))
    }

    @Test
    fun `21점 이상 여부 확인`() {
        assertThat(Point.isReachMaxPoint(Point(21))).isTrue()
    }
}

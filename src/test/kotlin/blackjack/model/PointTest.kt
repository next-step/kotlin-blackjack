package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointTest {

    @Test
    fun `첫 장이 ACE 인 경우 두 장의 점수`() {
        val point = Point(Kinds.ACE.point)

        val calculatePoints = Point.calculateAccIfAceFirst(point)

        assertThat(calculatePoints).isEqualTo(Point(11))
    }

    @Test
    fun `ACE 카드를 뽑을 때 추가 점수를 받을 수 있는 경우`() {
        val currentTotalPoint = Point(Kinds.EIGHT.point) + Point(Kinds.TWO.point)
        val point = Point(Kinds.ACE.point)

        assertThat(Point.isAvailableExtraPoint(currentTotalPoint, point)).isEqualTo(true)
    }

    @Test
    fun `ACE 카드를 뽑을 때 추가 점수를 못받는 경우`() {
        val currentTotalPoint = Point(Kinds.NINE.point) + Point(Kinds.TWO.point)
        val point = Point(Kinds.ACE.point)

        assertThat(Point.isAvailableExtraPoint(currentTotalPoint, point)).isEqualTo(false)
    }

    @Test
    fun `최대 점수 이상인 경우`() {
        val totalPoint = Point(Kinds.TEN.point) + Point(Kinds.TEN.point) + Point(Kinds.TWO.point)

        assertThat(Point.isReachMaxPoint(totalPoint)).isEqualTo(true)
    }
}

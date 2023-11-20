package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `demomination 리스트가 주어졌을 경우 Score 점수로 변환해 합산한다`() {
        // given
        val denominations = listOf(
            Denomination.TWO,
            Denomination.THREE,
            Denomination.FOUR,
            Denomination.FIVE
        )
        val score = Score(denominations)

        // when
        val result = score.calculate()

        // then
        assertEquals(14, result)
    }

    @Test
    fun `ACE denomination을 받았을 때 블랙잭 점수(21점)보다 클 경우 1점을 선택한다`() {
        // given
        val denominations = listOf(
            Denomination.ACE,
            Denomination.ACE
        )
        val score = Score(denominations)

        // when
        val result = score.calculate()

        // then
        assertEquals(12, result)
    }

    @Test
    fun `ACE denomination을 받았을 때 블랙잭 점수(21점)보다 작을 경우 11점을 선택한다`() {
        // given
        val denominations = listOf(
            Denomination.ACE,
            Denomination.TWO
        )
        val score = Score(denominations)

        // when
        val result = score.calculate()

        // then
        assertEquals(13, result)
    }
}

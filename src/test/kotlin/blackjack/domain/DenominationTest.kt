package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DenominationTest {
    @Test
    fun `점수를 계산한다 2 + 3 + 4 = 9`() {
        val result = Denomination.TWO.calculateScore(
            listOf(
                Denomination.THREE,
                Denomination.FOUR
            )
        )
        assertThat(result).isEqualTo(Score(9))
    }

    @Test
    fun `에이스는 기본적으로 11점이다 11 + 2 + 2 = 15`() {
        val result = Denomination.ACE.calculateScore(
            listOf(
                Denomination.TWO,
                Denomination.TWO
            )
        )
        assertThat(result).isEqualTo(Score(15))
    }

    @Test
    fun `점수 결과가 21이 넘는 경우 에이스는 1점으로 계산한다 1 + 2 + 9 = 12`() {
        val result = Denomination.ACE.calculateScore(
            listOf(
                Denomination.TWO,
                Denomination.NINE
            )
        )
        assertThat(result).isEqualTo(Score(12))
    }
}

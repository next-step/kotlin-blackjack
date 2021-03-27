package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DenominationTest {

    @Test
    fun `denomination 으로 점수 계산이 가능하다 TWO(2) + 1 = 3`() {
        assertThat(Denomination.TWO.calculateScore(Score.of(1))).isEqualTo(Score.of(3))
    }

    @Test
    fun `denomination 으로 점수 계산이 가능하다 ACE(11) + 10 = 21`() {
        assertThat(Denomination.ACE.calculateScore(Score.of(10))).isEqualTo(Score.of(21))
    }

    @Test
    fun `denomination 이 ACE 일 때 합이 21점을 초과 한다면 ACE는 1점으로 취급한다 ACE(1) + 11 = 12`() {
        assertThat(Denomination.ACE.calculateScore(Score.of(11))).isEqualTo(Score.of(12))
    }
}

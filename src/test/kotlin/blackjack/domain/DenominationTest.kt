package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class DenominationTest {

    @Test
    fun `카드 포인트 종류는 13개로 구성된다`() {
        assertThat(Denomination.values()).hasSize(13)
    }

    @Test
    fun `Ace는 1 혹은 11포인트를 리턴한다`() {
        assertThat(Denomination.ACE.point).isEqualTo(Pair(1, 11))
    }

    @ParameterizedTest
    @EnumSource(names = ["JACK", "QUEEN", "KING"])
    fun `숫자가 아닌 알파벳 수는 점수가 10점으로 계산된다`(denomination: Denomination) {
        assertThat(denomination.point.first).isEqualTo(10)
    }
}

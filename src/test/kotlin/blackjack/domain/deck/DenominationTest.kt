package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class DenominationTest {
    @ParameterizedTest
    @EnumSource(Denomination::class, names = ["ACE", "JACK", "QUEEN", "KING"], mode = EnumSource.Mode.EXCLUDE)
    fun `2부터10까지의_카드는_끗수와_점수가_동일하다`(denomination: Denomination) {
        assertThat(denomination.point).isEqualTo(denomination.denomination.toInt())
    }

    @ParameterizedTest
    @EnumSource(Denomination::class, names = ["JACK", "QUEEN", "KING"], mode = EnumSource.Mode.INCLUDE)
    fun `잭_퀸_킹은_10점`(denomination: Denomination) {
        assertThat(denomination.point).isEqualTo(10)
    }

    @Test
    fun `에이스는_기본_11점`() {
        assertThat(Denomination.ACE.point).isEqualTo(11)
    }
}

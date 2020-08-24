package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DenominationTest {
    @Test
    fun has_13denomination() {
        assertThat(Denomination.values()).hasSize(13)
        assertThat(Denomination.ACE.symbol).isEqualTo("A")
        assertThat(Denomination.ACE.number).isEqualTo(1)
    }
}

package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DenominationTest {
    @Test
    fun get_13_denomination() {
        assertThat(Denomination.values()).hasSize(13)
    }

    @Test
    fun get_number() {
        assertThat(Denomination.TWO.number).isEqualTo(2)
    }
}

package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun make_ace_card() {
        val ace = Card("♠A", 1)

        assertThat(ace.name).isEqualTo("♠A")
        assertThat(ace.number).isEqualTo(1)
    }
}

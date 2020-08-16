package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun make_ace_card() {
        val ace = Card(Shape.SPADE, Denomination.ACE)

        assertThat(ace.shape).isEqualTo(Shape.SPADE)
        assertThat(ace.denomination).isEqualTo(Denomination.ACE)
    }

    @Test
    fun get_card_name() {
        val ace = Card(Shape.SPADE, Denomination.ACE)

        val name = ace.toString()

        assertThat(name).isEqualTo("♠A")
    }
}

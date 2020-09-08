package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun make_spade_ace() {
        val spadeAce = Card(Shape.SPADE, Denomination.ACE)

        assertThat(spadeAce.toString()).isEqualTo("♠A")
    }

    @Test
    fun get_card_number() {
        val spadeAce = Card(Shape.SPADE, Denomination.ACE)

        assertThat(spadeAce.number).isEqualTo(1)
    }
}

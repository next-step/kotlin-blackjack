package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ShapeTest {
    @Test
    fun make_card() {
        val spadeAce = Shape.SPADE.makeCard(1)

        assertThat(spadeAce).isEqualTo(Card("♠A", 1))
    }
}

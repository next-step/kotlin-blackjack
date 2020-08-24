package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ShapeTest {
    @Test
    fun shape_has_4shape_spade_clover_heart_dia() {
        assertThat(Shape.values()).hasSize(4)
        assertThat(Shape.SPADE.shape).isEqualTo("♠")
        assertThat(Shape.CLOVER.shape).isEqualTo("♣")
        assertThat(Shape.HEART.shape).isEqualTo("♥")
        assertThat(Shape.DIA.shape).isEqualTo("♦")
    }
}

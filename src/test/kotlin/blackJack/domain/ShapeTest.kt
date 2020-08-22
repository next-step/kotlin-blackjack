package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ShapeTest {
    @Test
    fun get_four_shape() {
        assertThat(Shape.values()).hasSize(4)
    }
}

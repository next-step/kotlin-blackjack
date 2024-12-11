package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CarTest {
    @Test
    fun move() {
        val car = Car("jason", 0)
        val actual: Car = car.move2 { true }
        assertThat(actual).isEqualTo(Car("jason", 1))
    }

    @Test
    fun stop() {
        val car = Car("jason", 0)
        val actual: Car = car.move2 { false }
        assertThat(actual).isEqualTo(Car("jason", 0))
    }
}

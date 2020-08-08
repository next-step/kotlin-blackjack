package lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CarTest {
    @Test
    fun go() {
        val car = Car("jason", 0)
        val actual: Car = car.move { true }
        assertThat(actual).isEqualTo(Car("jason", 1))
    }

    @Test
    fun stop() {
        val car = Car("jason", 0)
        val actual: Car = car.move { false }
        assertThat(actual).isEqualTo(Car("jason", 0))
    }
}

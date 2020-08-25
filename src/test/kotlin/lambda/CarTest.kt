package lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CarTest {
    @Test
    fun move() {
        val car = Car("car", 0)
        val actual: Car = car.move { true }
        assertThat(actual).isEqualTo(Car("car", 1))
    }

    @Test
    fun stop() {
        val car = Car("car", 0)
        val actual: Car = car.move { false }
        assertThat(actual).isEqualTo(Car("car", 0))
    }
}

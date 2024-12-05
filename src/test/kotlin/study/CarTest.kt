package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CarTest {
    @Test
    fun 이동() {
        val car = Car("jason", 0)
        val actual: Car = car.move2 { true }
        assertThat(actual).isEqualTo(Car("jason", 1))
    }

    @Test
    fun 정지() {
        val car = Car("jason", 0)
        val actual: Car = car.move2 { false }
        assertThat(actual).isEqualTo(Car("jason", 0))
    }
}

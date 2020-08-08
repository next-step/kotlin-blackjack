package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CarTest {
    @Test
    fun 이동() {
        val car = Car("harry", 0)
        val actual: Car = car.move(4) { true }
        assertThat(actual).isEqualTo(Car("harry", 1))
    }

    @Test
    fun 정지() {
        val car = Car("harry", 0)
        val actual: Car = car.move(0) { false }
        assertThat(actual).isEqualTo(Car("harry", 0))
    }
}

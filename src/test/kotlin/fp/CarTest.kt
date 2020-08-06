package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CarTest {
    @Test
    fun move() {
        val car = Car("josephmjjo", 0)
        val actual: Car = car.move { true } // 맨마지막이 인자인경
        assertThat(actual).isEqualTo(Car("josephmjjo", 1))
    }

    @Test
    fun stop() {
        val car = Car("josepmjjo", 0)
        val actual: Car = car.move { false }

        assertThat(actual).isEqualTo(Car("josepmjjo", 0))
    }
}

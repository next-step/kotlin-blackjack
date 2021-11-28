package learning

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CarTests {
    @Test
    fun `이동`() {
        val car = Car("hodol", 0)
        val actual: Car = car.moved { true }
        val expected = Car("hodol", 1)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `정지`() {
        val car = Car("hodol", 0)
        val actual: Car = car.moved { false }
        val expected = Car("hodol", 0)
        assertThat(actual).isEqualTo(expected)
    }
}

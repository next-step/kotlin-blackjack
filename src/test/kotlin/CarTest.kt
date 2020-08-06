import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CarTest {
    @Test
    fun `이동`() {
        val car = Car("jiwhun", 0)
        val moveAble: () -> Boolean = { true }
        val actual: Car = car.move(moveAble)
        assertThat(actual).isEqualTo(Car("jiwhun", 1))
    }

    @Test
    fun `정지`() {
        val car = Car("jiwhun", 0)
        val moveAble: () -> Boolean = { false }
        val actual: Car = car.move(moveAble)
        assertThat(actual).isEqualTo(Car("jiwhun", 0))
    }
}

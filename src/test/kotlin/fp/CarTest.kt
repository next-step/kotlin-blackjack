package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.function.Supplier

class CarTest {

    @Test
    fun `Car move test`() {
        val car = Car("hyunsung", 0)
        val actual: Car = car.move(Supplier { true })
        assertThat(actual).isEqualTo(Car("hyunsung", 1))
    }

    @Test
    fun `Car stop test`() {
        val car = Car("hyunsung", 0)
        val actual: Car = car.move { false }
        assertThat(actual).isEqualTo(Car("hyunsung", 0))
    }
}

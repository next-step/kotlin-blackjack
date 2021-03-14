package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CarTest {

    @Test
    fun 이동() {

        val car = Car("jason", 0)
        val actual: Car = car.move{true}

        assertThat(actual).isEqualTo(Car("jason", 1))
    }

    @Test
    fun 정지() {
        val car = Car("jason", 0)
        val movable = {false}

        val actual: Car = car.move(movable)
        assertThat(actual).isEqualTo(Car("jason", 0))
    }
}

data class Car(val name: String, val position: Int) {
    fun move(isMovable:() -> Boolean): Car {
        if (isMovable()) {
            return copy(position = position + 1)
        }
        return this
    }
}

interface MoveStrategy {
    val isMovable: Boolean
}


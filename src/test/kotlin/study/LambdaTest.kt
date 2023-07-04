package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {

    class CarTest {

        @Test
        fun 이동() {
            var car = Car("gray", 0)
            val actual: Car = car.move { true }
            assertThat(actual).isEqualTo(Car("gray", 1))
        }

        @Test
        fun 정지() {
            val car = Car("gray", 0)
            val actual: Car = car.move { false }
            assertThat(actual).isEqualTo(Car("gray", 0))
        }
    }

    data class Car(val name: String, val position: Int) {
        fun move(moveStrategy: () -> Boolean): Car {
            if (moveStrategy()) {
                return copy(position = position + 1)
            }
            return this
        }
    }

    class DeDuplicationTest {
        val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

        @Test
        fun sumAllTest() {
            val actual = sumAll(numbers)
            assertThat(actual).isEqualTo((1..6).sum())
        }

        @Test
        fun sumAllEvenTest() {
            val actual = sumAllEven(numbers)
            assertThat(actual).isEqualTo((listOf(2, 4, 6).sum()))
        }

        @Test
        fun sumAllOverThreeTest() {
            val actual = sumAllOverThree(numbers)
            assertThat(actual).isEqualTo((listOf(4, 5, 6).sum()))
        }

        fun sumAll(numbers: List<Int>): Int {
            return numbers.sum()
        }

        fun sumAllEven(numbers: List<Int>): Int {
            return numbers.filter { it % 2 == 0 }.sum()
        }

        fun sumAllOverThree(numbers: List<Int>): Int {
            return numbers.filter { it > 3 }.sum()
        }
    }
}

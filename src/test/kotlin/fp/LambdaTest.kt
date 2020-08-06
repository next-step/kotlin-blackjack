package fp

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {
    val lambda = Lambda()

    @Test
    fun `sum all`() {
        val actual: Int = lambda.sumInCondition(lambda.numbers) { true }
        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `sum all even`() {
        val actual: Int = lambda.sumInCondition(lambda.numbers) { number -> number % 2 == 0 }
        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `sum all overt three`() {
        val actual: Int = lambda.sumInCondition(lambda.numbers) { number -> number > 3 }
        assertThat(actual).isEqualTo(15)
    }
}

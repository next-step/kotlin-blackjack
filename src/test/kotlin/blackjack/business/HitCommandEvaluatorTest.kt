package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HitCommandEvaluatorTest {

    @ParameterizedTest
    @CsvSource(value = ["y,true", "n,false", "Y,false", "N,false", "yes,false", "no,false"])
    fun `입력 명령어를 확인한다`(command: String, expected: Boolean) {
        // when
        val actual = HitCommandEvaluator.evaluate(command)

        // then
        actual shouldBe expected
    }
}

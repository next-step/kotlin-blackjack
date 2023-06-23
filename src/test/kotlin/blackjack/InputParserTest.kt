package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class InputParserTest {

    @Test
    fun `쉼표를 기준으로 이름을 구분한다`() {
        val input = "a, b, c, d, e"
        InputParser.parse(input).size shouldBe 5
    }
}

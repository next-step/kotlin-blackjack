package blackjack.ui.input

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StringAnswerParserTest {
    @Test
    fun `'y'를 입력하면 true를 반환한다`() {
        val answer = StringAnswerParser.answerParse("y")
        Assertions.assertThat(answer).isEqualTo(true)
    }

    @Test
    fun `'n'를 입력하면 false를 반환한다`() {
        val answer = StringAnswerParser.answerParse("n")
        Assertions.assertThat(answer).isEqualTo(false)
    }

    @Test
    fun `'그외 문자는' IllegalArgumentException을 반환한다`() {
        assertThrows<IllegalArgumentException> { StringAnswerParser.answerParse("yyy") }
    }
}

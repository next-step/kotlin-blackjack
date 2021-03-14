package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserInputTest {
    @Test
    fun `쉼표로 구분된 문자를 입력받는다`() {
        val userInput = UserInput.StringList("질문", "pobi,json\n")
        assertThat(userInput.answer()).isEqualTo(listOf("pobi", "json"))
    }

    @Test
    fun `y 를 받는다`() {
        val userInput = UserInput.Char("질문", "y\n")
        assertThat(userInput.answer()).isEqualTo('y')
    }
}

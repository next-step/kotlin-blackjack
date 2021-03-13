package blackjack

import org.junit.jupiter.api.Test

class UserInputTest {
    @Test
    fun `쉼표로 구분된 문자를 입력받는다`() {
        val userInput = UserInput.StringArray("질문", "pobi,json\n")
        assertThat(userInput.answer()).isEqualTo(listOf("pobi", "json"))
    }
}

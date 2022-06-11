package blackjack

import blackjack.domain.UserAnswer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class UserActionTest {
    @ParameterizedTest
    @ValueSource(strings = ["y", "n"])
    fun `알파벳이 유효한 유저 인풋인지 판단이 맞는지 테스트`(input: String) {
        assertThat(UserAnswer.isValidAnswer(input)).isTrue
    }

    @Test
    fun `유저 인풋 스트링에 맞는 유저엑션값이 나오는지`() {
        val yesInput = "y"
        val yesAnswer = UserAnswer.getUserAnswer(yesInput)
        assertThat(yesAnswer).isEqualTo(UserAnswer.YES)

        val noInput = "n"
        val noAnswer = UserAnswer.getUserAnswer(noInput)
        assertThat(noAnswer).isEqualTo(UserAnswer.NO)
    }
}

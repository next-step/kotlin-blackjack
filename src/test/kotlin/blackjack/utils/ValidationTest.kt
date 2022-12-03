package blackjack.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ValidationTest {

    @Test
    @DisplayName("입력 값이 n일 경우 true")
    fun `True if the input value is n`() {
        val answer = "n"
        val isAnswer = Validation.isAnswer(answer)
        assertThat(isAnswer).isTrue
    }

    @Test
    @DisplayName("입력 값이 k일 경우 false")
    fun `False if input value is k`() {
        val answer = "k"
        val isAnswer = Validation.isAnswer(answer)
        assertThat(isAnswer).isFalse
    }
}

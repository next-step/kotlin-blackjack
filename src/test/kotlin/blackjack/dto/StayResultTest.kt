package blackjack.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StayResultTest {

    @Test
    @DisplayName("카드를 한장 더 받는 입력 값이 y 혹은 n이 아닌 경우 IllegalArgumentException 오류")
    fun `IllegalArgumentException error if input value is not y or n`() {
        val answer = "u"
        assertThrows<IllegalArgumentException> { StayResult(answer) }
    }

    @Test
    @DisplayName("카드를 한장 더 받는 입력 값이 y인 경우 true")
    fun `True if the input value is y`() {
        val answer = "y"
        assertThat(StayResult(answer).isStay).isTrue
    }

    @Test
    @DisplayName("카드를 한장 더 받는 입력 값이 n인 경우 false")
    fun `False if the input value is n`() {
        val answer = "n"
        assertThat(StayResult(answer).isStay).isFalse
    }
}

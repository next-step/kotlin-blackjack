package blackjack.ui

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InputViewTest {
    @Test
    fun `유저는 ,로 구분되어 여러명 입력 가능하다`() {
        val inputView = InputView { "a,b" }

        val actual = inputView.inputUserNames()

        assertThat(actual).containsExactly("a", "b")
    }

    @Test
    fun `더 플레이 할지 입력받는다`() {
        val inputView = InputView { "y" }

        val actual = inputView.inputMore("userA")

        assertThat(actual).isTrue()
    }
}
package blackjack.ui

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultViewTest {
    @Test
    fun `유저의 이름과 장수를 출력한다`() {
        // given
        var expected = ""
        val customOutputProvider: (String) -> Unit = { message -> expected = message }
        val resultView = ResultView(customOutputProvider)

        resultView.printUserCardCount(listOf("userA", "userB"), 2)

        assertThat(expected).contains("userA, userB에게 2장의 나누었습니다.")
    }

    @Test
    fun `유저 카드 출력`() {
        // given
        var expected = ""
        val customOutputProvider: (String) -> Unit = { message -> expected = message }
        val resultView = ResultView(customOutputProvider)

        resultView.printUserCards("userA", listOf("2하트", "2스페이드"))

        assertThat(expected).contains("userA카드: 2하트, 2스페이드")
    }

    @Test
    fun `결과 출력`() {
        // given
        var expected = ""
        val customOutputProvider: (String) -> Unit = { message -> expected = message }
        val resultView = ResultView(customOutputProvider)

        resultView.printResult("userA", listOf("2하트", "8스페이드", "A클로버"), 21)

        assertThat(expected).contains("userA카드: 2하트, 8스페이드, A클로버 - 결과: 21")
    }
}

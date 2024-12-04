package blackjack.ui

import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UIMatchTypeViewTest {
    @Test
    fun `게임을 시작하면 딜러와 유저들은 카드를 받는다`() {
        // given
        var expected = ""
        val customOutputProvider: (String) -> Unit = { message -> expected = message }
        val resultView = ResultView(customOutputProvider)

        resultView.printUserCardCount("딜러", listOf("userA", "userB"), 2)

        assertThat(expected).isEqualTo("딜러와 userA, userB에게 2장의 나누었습니다.")
    }

    @Test
    fun `유저 카드 출력`() {
        var expected = ""
        val customOutputProvider: (String) -> Unit = { message -> expected = message }
        val resultView = ResultView(customOutputProvider)

        resultView.printRound("userA", mapOf(CardType.TWO.name to listOf(Suit.HEART.name, Suit.SPADE.name)))

        assertThat(expected).contains("userA카드: 2하트, 2스페이드")
    }

    @Test
    fun `결과 출력`() {
        var actualMessage = ""
        val customOutputProvider: (String) -> Unit = { message -> actualMessage = message }
        val resultView = ResultView(customOutputProvider)

        resultView.printResult(
            mapOf(
                "userA" to
                    mapOf(
                        mapOf(CardType.TWO.name to listOf(Suit.HEART.name, Suit.SPADE.name)) to 4,
                    ),
            ),
        )

        assertThat(actualMessage).contains("userA카드: 2하트, 2스페이드 - 결과: 4")
    }
}

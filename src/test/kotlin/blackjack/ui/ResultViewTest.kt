package blackjack.ui

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ResultViewTest {
    @Test
    fun `점수계산_다수의카드합산`() {
        var actualMessage = ""
        val customOutputProvider: (String) -> Unit = { message -> actualMessage += message }
        val resultView = ResultView(customOutputProvider)

        resultView.printScoreResult(
            listOf(
                RoundResult(
                    "유저A",
                    listOf(
                        DisplayCard.from("TWO", "HEART"),
                        DisplayCard.from("EIGHT", "SPADE"),
                        DisplayCard.from("ACE", "SPADE"),
                    ),
                    21,
                ),
                RoundResult(
                    "유저B",
                    listOf(
                        DisplayCard.from("SEVEN", "SPADE"),
                        DisplayCard.from("KING", "SPADE"),
                    ),
                    17,
                ),
                RoundResult(
                    "딜러",
                    listOf(
                        DisplayCard.from("THREE", "DIAMOND"),
                        DisplayCard.from("NINE", "SPADE"),
                        DisplayCard.from("EIGHT", "DIAMOND"),
                    ),
                    20,
                ),
            ),
        )

        assertAll(
            { assert(actualMessage.contains("딜러카드: 3다이아몬드, 9스페이드, 8다이아몬드 - 결과: 20")) },
            { assert(actualMessage.contains("유저A카드: 2하트, 8스페이드, A스페이드 - 결과: 21")) },
            { assert(actualMessage.contains("유저B카드: 7스페이드, K스페이드 - 결과: 17")) },
        )
    }

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

        resultView.printRound(
            "userA",
            listOf(DisplayCard.from("TWO", "HEART"), DisplayCard.from("TWO", "SPADE")),
        )

        assertThat(expected).contains("userA카드: 2하트, 2스페이드")
    }

    @Test
    fun `결과 출력`() {
        var actualMessage = ""
        val customOutputProvider: (String) -> Unit = { message -> actualMessage = message }
        val resultView = ResultView(customOutputProvider)

        resultView.printScoreResult(
            listOf(
                RoundResult(
                    "userA",
                    listOf(DisplayCard.from("TWO", "HEART"), DisplayCard.from("TWO", "SPADE")),
                    4,
                ),
            ),
        )

        assertThat(actualMessage).contains("userA카드: 2하트, 2스페이드 - 결과: 4")
    }

    @Test
    fun `소유카드를 출력한다`() {
        var actualMessage = ""
        val customOutputProvider: (String) -> Unit = { message -> actualMessage = message }
        val resultView = ResultView(customOutputProvider)

        resultView.printUserCards(
            listOf(
                RoundResult(
                    "userA",
                    listOf(DisplayCard.from("TWO", "HEART"), DisplayCard.from("TWO", "SPADE")),
                    4,
                ),
            ),
        )

        assertThat(actualMessage).contains("userA카드: 2하트, 2스페이드")
    }

    @Test
    fun `소유 카드와 스코를 출력한다`() {
        var actualMessage = ""
        val customOutputProvider: (String) -> Unit = { message -> actualMessage = message }
        val resultView = ResultView(customOutputProvider)

        resultView.printScoreResult(
            listOf(
                RoundResult(
                    "userA",
                    listOf(DisplayCard.from("TWO", "HEART"), DisplayCard.from("TWO", "SPADE")),
                    4,
                ),
            ),
        )

        assertThat(actualMessage).contains("userA카드: 2하트, 2스페이드 - 결과: 4")
    }

    @Test
    fun `최종 결과를 출력한다`() {
        var actualMessage = ""
        val customOutputProvider: (String) -> Unit = { message -> actualMessage = actualMessage + message + "\n" }
        val resultView = ResultView(customOutputProvider)

        resultView.printFinalWinner(
            FinalWinnerResults(
                DealerResult(1, 1, 0),
                mapOf(
                    "userA" to UIMatchType.WIN,
                    "userB" to UIMatchType.LOSS,
                ),
            ),
        )

        assertThat(actualMessage).contains("딜러: 1승 1패 0무")
        assertThat(actualMessage).contains("userA: 승")
        assertThat(actualMessage).contains("userB: 패")
    }

    private fun createTestResultView(messages: MutableList<String>): ResultView {
        val customOutputProvider: (String) -> Unit = { message -> messages.add(message) }
        return ResultView(customOutputProvider)
    }
}

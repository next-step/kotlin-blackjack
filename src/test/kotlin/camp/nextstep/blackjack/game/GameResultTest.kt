package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.game.GameResult.Companion.reversedResults
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class GameResultTest {

    @DisplayName("게임 결과는 승 / 패 / 무 중 하나이다.")
    @Test
    fun gameResult() {
        assertThat(GameResult.values()).containsExactly(GameResult.WIN, GameResult.LOSE, GameResult.DRAW)
    }

    @DisplayName("결과를 뒤집으면 상대방의 결과를 알 수 있다.")
    @Test
    fun reverseResult() {
        val myResult = listOf(GameResult.WIN, GameResult.WIN, GameResult.LOSE, GameResult.DRAW)
        val opponentResult = myResult.reversedResults()

        assertThat(opponentResult.count { it == GameResult.WIN }).isEqualTo(myResult.count { it == GameResult.LOSE })
        assertThat(opponentResult.count { it == GameResult.LOSE }).isEqualTo(myResult.count { it == GameResult.WIN })
        assertThat(opponentResult.count { it == GameResult.DRAW }).isEqualTo(myResult.count { it == GameResult.DRAW })
    }

    @DisplayName("승패 테스트")
    @ParameterizedTest(name = "{0} 과 {1} 중에 승자는 {0}")
    @CsvSource(
        delimiter = ',',
        value = [
            "10, 1",
            "10, 5",
            "20, 9",
            "20, 15",
            "21, 15",
            "21, 20",
            "1, 22",
            "10, 22",
            "21, 22",
        ]
    )
    fun gameWinner(winnerScore: Int, loserScore: Int) {
        assertThat(GameResult.of(Score.of(winnerScore), Score.of(loserScore))).isEqualTo(GameResult.WIN)
    }

    @DisplayName("무승부 테스트")
    @ParameterizedTest(name = "{0} 과 {1} 은 무승부")
    @CsvSource(
        delimiter = ',',
        value = [
            "1, 1",
            "10, 10",
            "21, 21",
            "22, 22",
            "23, 22",
            "22, 23",
        ]
    )
    fun gameDrawer(winnerScore: Int, loserScore: Int) {
        assertThat(GameResult.of(Score.of(winnerScore), Score.of(loserScore))).isEqualTo(GameResult.DRAW)
    }
}

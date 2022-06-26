package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.game.GameResult.Companion.reversedResults
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

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
}

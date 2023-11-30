package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameResultsTest {
    @Test
    fun `주어진 게임 결과와 반대값을 가진 리스트를 반환한다`() {
        // given
        val gameResults = GameResults(
            listOf(
                GameResult.WIN,
                GameResult.LOSE,
                GameResult.DRAW
            )
        )

        // when
        val result = gameResults.getOpposite()

        // then
        assertEquals(GameResult.LOSE, result[0])
        assertEquals(GameResult.WIN, result[1])
        assertEquals(GameResult.DRAW, result[2])
    }
}

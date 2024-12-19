package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class GameResultTest {
    @DisplayName("딜러와 플레이어 점수가 주어졌을 때 게임 결과를 알맞게 반환한다")
    @ParameterizedTest
    @MethodSource("gameResultProvider")
    fun testGetGameResultsWith(
        isPlayerBlackJackInitially: Boolean,
        isDealerBlackJackInitially: Boolean,
        dealerCardSum: Int,
        playerCardSum: Int,
        expected: GameResult,
    ) {
        val result =
            GameResult.getGameResultsWith(
                isPlayerBlackJackInitially,
                isDealerBlackJackInitially,
                dealerCardSum,
                playerCardSum,
            )
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        fun gameResultProvider(): List<Arguments> {
            return listOf(
                Arguments.of(true, false, 20, 21, GameResult.BLACK_JACK),
                Arguments.of(true, true, 21, 21, GameResult.PUSH),
                Arguments.of(false, false, 22, 18, GameResult.WIN),
                Arguments.of(false, false, 20, 22, GameResult.BUST),
                Arguments.of(false, false, 20, 19, GameResult.LOSE),
                Arguments.of(false, false, 19, 20, GameResult.WIN),
                Arguments.of(false, false, 20, 20, GameResult.PUSH),
            )
        }
    }
}

package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameResultTest {
    @DisplayName("딜러와 플레이어 점수가 주어졌을 때 게임 결과를 알맞게 반환한다")
    @ParameterizedTest(name = "{index} => dealerScore={0}, playerScore={1}, expectedResult={2}")
    @CsvSource(
        // Dealer == Bust, Player 승리
        "22, 20, WIN",
        // Player == Bust, Dealer 승리
        "20, 22, BUST",
        // 둘다 Bust 아닐때 Player 점수가 높으면, Player 승리
        "18, 19, WIN",
        // 둘다 Bust 아닐때 Dealer 점수가 높으면, Dealer 승리
        "19, 18, LOSE",
    )
    fun getGameResultFromDealerAndPlayerScores(
        dealerScore: Int,
        playerScore: Int,
        expectedResult: GameResult,
    ) {
        val result =
            GameResult.fromScores(
                dealerScore = dealerScore,
                playerScore = playerScore,
            )
        assertEquals(expectedResult, result)
    }
}

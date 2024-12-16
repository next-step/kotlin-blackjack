package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameResultCalculatorTest {
    @DisplayName("딜러의 점수와 플레이어의 점수가 주어졌을 때 알맞은 게임 결과를 계산한다")
    @ParameterizedTest(name = "{index} => dealerScore={0}, playerScore={1}, expectedResult={2}")
    @CsvSource(
        "22, 20, WIN",  // Dealer == Bust, Player 승리
        "20, 22, LOSE", // Player == Bust, Dealer 승리
        "18, 19, WIN",  // 둘다 Bust 아닐때 Player 점수가 높으면, Player 승리
        "19, 18, LOSE"  // 둘다 Bust 아닐때 Dealer 점수가 높으면, Dealer 승리
    )
    fun `should calculate game result correctly based on dealer and player scores`(
        dealerScore: Int,
        playerScore: Int,
        expectedResult: GameResult
    ) {
        val result = PlayerResultCalculator.calculate(
            dealerScore = dealerScore,
            playerScore = playerScore
        )
        assertEquals(expectedResult, result)
    }
}

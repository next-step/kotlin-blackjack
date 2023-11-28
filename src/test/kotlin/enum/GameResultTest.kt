package enum

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    @DisplayName("플레이어 점수가 21을 초과하면 패배한다")
    fun `플레이어 점수가 21을 초과하면 패배`() {
        val playerScore = 22
        val dealerScore = 20
        val playerHasBlackjack = false

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerHasBlackjack)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    @DisplayName("딜러 점수가 21을 초과하면 플레이어가 승리한다")
    fun `딜러 점수가 21을 초과하면 승리`() {
        val playerScore = 20
        val dealerScore = 22
        val playerHasBlackjack = false

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerHasBlackjack)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 높으면 승리한다")
    fun `플레이어 점수가 딜러 점수보다 높으면 승리`() {
        val playerScore = 20
        val dealerScore = 18
        val playerHasBlackjack = false

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerHasBlackjack)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    @DisplayName("플레이어 점수와 딜러 점수가 같으면 무승부다")
    fun `플레이어 점수와 딜러 점수가 같으면 무승부`() {
        val playerScore = 20
        val dealerScore = 20
        val playerHasBlackjack = false

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerHasBlackjack)
        assertEquals(GameResult.DRAW, result)
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 낮으면 패배한다")
    fun `플레이어 점수가 딜러 점수보다 낮으면 패배`() {
        val playerScore = 17
        val dealerScore = 20
        val playerHasBlackjack = false

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerHasBlackjack)
        assertEquals(GameResult.LOSE, result)
    }
}

package enum

import domain.Card
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    @DisplayName("플레이어 점수가 21을 초과하면 패배한다")
    fun `플레이어 점수가 21을 초과하면 패배`() {
        val playerScore = 22
        val dealerScore = 20
        val playerCards = listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.DIAMONDS, Rank.QUEEN), Card(Suit.CLUBS, Rank.THREE))
        val dealerCards = listOf(Card(Suit.SPADES, Rank.ACE), Card(Suit.HEARTS, Rank.NINE))

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerCards, dealerCards)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    @DisplayName("딜러 점수가 21을 초과하면 플레이어가 승리한다")
    fun `딜러 점수가 21을 초과하면 승리`() {
        val playerScore = 20
        val dealerScore = 22
        val playerCards = listOf(Card(Suit.HEARTS, Rank.NINE), Card(Suit.CLUBS, Rank.ACE))
        val dealerCards = listOf(Card(Suit.DIAMONDS, Rank.QUEEN), Card(Suit.SPADES, Rank.KING), Card(Suit.HEARTS, Rank.THREE))

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerCards, dealerCards)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 높으면 승리한다")
    fun `플레이어 점수가 딜러 점수보다 높으면 승리`() {
        val playerScore = 20
        val dealerScore = 18
        val playerCards = listOf(Card(Suit.SPADES, Rank.TEN), Card(Suit.CLUBS, Rank.QUEEN))
        val dealerCards = listOf(Card(Suit.HEARTS, Rank.SEVEN), Card(Suit.DIAMONDS, Rank.JACK))

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerCards, dealerCards)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    @DisplayName("플레이어 점수와 딜러 점수가 같으면 무승부다")
    fun `플레이어 점수와 딜러 점수가 같으면 무승부`() {
        val playerScore = 20
        val dealerScore = 20
        val playerCards = listOf(Card(Suit.DIAMONDS, Rank.TEN), Card(Suit.HEARTS, Rank.QUEEN))
        val dealerCards = listOf(Card(Suit.CLUBS, Rank.KING), Card(Suit.SPADES, Rank.JACK))

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerCards, dealerCards)
        assertEquals(GameResult.DRAW, result)
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 낮으면 패배한다")
    fun `플레이어 점수가 딜러 점수보다 낮으면 패배`() {
        val playerScore = 17
        val dealerScore = 20
        val playerCards = listOf(Card(Suit.HEARTS, Rank.EIGHT), Card(Suit.DIAMONDS, Rank.NINE))
        val dealerCards = listOf(Card(Suit.CLUBS, Rank.TEN), Card(Suit.SPADES, Rank.QUEEN))

        val result = GameResult.determineForResultOfPlayer(playerScore, dealerScore, playerCards, dealerCards)
        assertEquals(GameResult.LOSE, result)
    }
}

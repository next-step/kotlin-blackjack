package domain

import enum.Rank
import enum.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Test
    @DisplayName("플레이어가 카드를 받으면 손패에 추가되어야 함")
    fun `플레이어가 카드를 받을 때`() {
        val player = Player("Jaeyeon", 10000)
        val card = Card(Suit.HEARTS, Rank.TWO)
        player.receiveCard(card)

        assertEquals(listOf(card), player.showHand())
    }

    @Test
    @DisplayName("플레이어의 점수 계산")
    fun `플레이어의 점수 계산`() {
        val player = Player("Jaeyeon", 10000)
        player.receiveCard(Card(Suit.HEARTS, Rank.KING))
        player.receiveCard(Card(Suit.SPADES, Rank.THREE))
        assertEquals(13, player.calculateScore())

        player.receiveCard(Card(Suit.CLUBS, Rank.ACE))
        assertEquals(14, player.calculateScore())
    }

    @Test
    @DisplayName("플레이어가 베팅 금액을 설정할 수 있어야 함")
    fun `플레이어의 베팅 금액 설정`() {
        val player = Player("Jaeyeon", 10000)

        assertEquals(10000, player.bettingAmount.amount())
    }

    @Test
    @DisplayName("플레이어의 베팅 금액은 음수가 될 수 없음")
    fun `플레이어의 베팅 금액 음수 설정 불가`() {
        val invalidAmount = -10000

        assertThrows<IllegalArgumentException> {
            Player("Jaeyeon", invalidAmount)
        }
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 승리할 경우 수익 계산")
    fun `플레이어의 블랙잭 승리 수익 계산`() {
        val player = Player("Jaeyeon", 10000)
        player.receiveCard(Card(Suit.HEARTS, Rank.ACE))
        player.receiveCard(Card(Suit.DIAMONDS, Rank.KING))
        player.determineResult(BlackjackRules.MAXIMUM_SCORE - 1, emptyList())

        val profit = player.calculateFinalProfit()
        assertEquals(15000, profit)
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭인 경우 베팅 금액 돌려받음")
    fun `딜러와 플레이어 모두 블랙잭인 경우 수익 계산`() {
        val player = Player("Jaeyeon", 10000)
        player.receiveCard(Card(Suit.HEARTS, Rank.ACE))
        player.receiveCard(Card(Suit.DIAMONDS, Rank.KING))
        player.determineResult(BlackjackRules.MAXIMUM_SCORE, listOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.DIAMONDS, Rank.KING)))

        val profit = player.calculateFinalProfit()
        assertEquals(0, profit)
    }

    @Test
    @DisplayName("플레이어가 승리할 경우 베팅 금액만큼 수익을 얻음")
    fun `플레이어 승리 시 수익 계산`() {
        val player = Player("Jaeyeon", 10000)
        player.receiveCard(Card(Suit.HEARTS, Rank.TEN))
        player.receiveCard(Card(Suit.DIAMONDS, Rank.NINE))
        player.determineResult(18, emptyList())

        val profit = player.calculateFinalProfit()
        assertEquals(10000, profit)
    }

    @Test
    @DisplayName("플레이어가 패배하거나 버스트인 경우 베팅 금액을 잃음")
    fun `플레이어 패배 또는 버스트 시 수익 계산`() {
        val player = Player("Jaeyeon", 10000)
        player.receiveCard(Card(Suit.HEARTS, Rank.TEN))
        player.receiveCard(Card(Suit.DIAMONDS, Rank.NINE))
        player.receiveCard(Card(Suit.CLUBS, Rank.FOUR))
        player.determineResult(20, emptyList())

        val profit = player.calculateFinalProfit()
        assertEquals(-10000, profit)
    }
}

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
        val player = Player("Jaeyeon")
        val card = Card(Suit.HEARTS, Rank.TWO)
        player.receiveCard(card)

        assertEquals(listOf(card), player.showHand())
    }

    @Test
    @DisplayName("플레이어의 점수 계산")
    fun `플레이어의 점수 계산`() {
        val player = Player("Jaeyeon")
        player.receiveCard(Card(Suit.HEARTS, Rank.KING))
        player.receiveCard(Card(Suit.SPADES, Rank.THREE))
        assertEquals(13, player.calculateScore())

        player.receiveCard(Card(Suit.CLUBS, Rank.ACE))
        assertEquals(14, player.calculateScore())
    }

    @Test
    @DisplayName("플레이어가 베팅 금액을 설정할 수 있어야 함")
    fun `플레이어의 베팅 금액 설정`() {
        val player = Player("Jaeyeon")
        val bettingAmount = 10000

        player.bettingAmount = bettingAmount
        assertEquals(bettingAmount, player.bettingAmount)
    }

    @Test
    @DisplayName("플레이어의 베팅 금액은 음수가 될 수 없음")
    fun `플레이어의 베팅 금액 음수 설정 불가`() {
        val player = Player("Jaeyeon")
        val invalidAmount = -10000

        assertThrows<IllegalArgumentException> {
            player.bettingAmount = invalidAmount
        }
    }
}

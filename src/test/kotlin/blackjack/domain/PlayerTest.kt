package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Finished
import blackjack.domain.state.Hit
import blackjack.domain.state.Running
import blackjack.domain.state.Stand
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    fun `새로운 카드를 받았을 때 카드 점수가 21점 미만일 경우 사용자의 상태는 기본적으로 Hit(Running)이다`() {
        // given
        val player = Player("test")
        val card = Card(Suit.SPADES, Denomination.ACE)
        // when
        player.receiveCard(card)
        // then
        assertEquals(true, player.state is Hit)
        assertEquals(true, player.state is Running)
    }

    @Test
    fun `새로운 카드를 받았을 때 카드 점수가 21점일 경우 사용자 상태는 Blackjack(Finished)으로 변경된다`() {
        // given
        val player = Player("test")
        val cardList = listOf(
            Card(Suit.SPADES, Denomination.TEN),
            Card(Suit.SPADES, Denomination.THREE),
            Card(Suit.SPADES, Denomination.EIGHT),
        )
        // when
        cardList.forEach(player::receiveCard)
        // then
        assertEquals(true, player.state is Blackjack)
        assertEquals(true, player.state is Finished)
    }

    @Test
    fun `새로운 카드를 받았을 때 카드 점수가 21점일 경우 사용자 상태는 Bust(Finished)로 변경된다`() {
        // given
        val player = Player("test")
        val cardList = listOf(
            Card(Suit.SPADES, Denomination.KING),
            Card(Suit.SPADES, Denomination.KING),
            Card(Suit.SPADES, Denomination.KING),
        )
        // when
        cardList.forEach(player::receiveCard)
        // then
        assertEquals(true, player.state is Bust)
        assertEquals(true, player.state is Finished)
    }

    @Test
    fun `새로운 카드를 받았을 때 카드 점수가 21점 미만일 경우에 사용자는 Stand(Finished)로 상태를 바꿀 수 있다`() {
        // given
        val player = Player("test")
        val card = Card(Suit.SPADES, Denomination.ACE)
        // when
        player.receiveCard(card)
        player.turnStand()
        // then
        assertEquals(true, player.state is Stand)
        assertEquals(true, player.state is Finished)
    }

    @Test
    fun `게임 시작시 받아야 할 카드 개수(INITIAL_DEAL_SIZE)보다 더 적거나 많은 카드를 받는다면 IllegalArgumentException을 발생시킨다`() {
        // given
        val player = Player("test")
        val cards1 = listOf(
            Card(Suit.SPADES, Denomination.ACE),
            Card(Suit.SPADES, Denomination.TWO),
            Card(Suit.SPADES, Denomination.KING),
        )
        val cards2 = listOf(
            Card(Suit.SPADES, Denomination.ACE),
        )

        assertThrows<IllegalArgumentException> { // then
            player.receiveInitialCards(cards1) // when
        }

        assertThrows<IllegalArgumentException> { // then
            player.receiveInitialCards(cards2) // when
        }
    }
}

package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `카드를 추가로 받을 수 있는 플레이어들만 필터링한다`() {
        // given
        val player1 = Player("player1")
        val player2 = Player("player2")
        val player3 = Player("player3")

        // when
        player1.receiveInitialCards(
            listOf(
                Card(Suit.SPADES, Denomination.TEN),
                Card(Suit.SPADES, Denomination.SIX),
            )
        )
        player2.receiveInitialCards(
            listOf(
                Card(Suit.SPADES, Denomination.TEN),
                Card(Suit.SPADES, Denomination.ACE)
            )
        )
        player3.receiveInitialCards(
            listOf(
                Card(Suit.SPADES, Denomination.TWO),
                Card(Suit.SPADES, Denomination.TWO),
            )
        )
        player3.turnStand()

        val players = Players(listOf(player1, player2, player3))

        // then
        assertEquals(1, players.withHit().size)
    }
}

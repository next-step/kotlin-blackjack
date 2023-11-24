package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `player들은 받은 카드를 나눠가지게 된다`() {
        // given
        val players = Players(
            listOf(
                Player("player1"),
                Player("player2"),
                Player("player3")
            )
        )

        // when
        players.receiveCards(
            listOf(
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.HEARTS, Denomination.TEN),
                Card(Suit.CLUBS, Denomination.TWO),
            )
        )

        // then
        assertEquals("A스페이드", players[0].cards.toString())
        assertEquals("10하트", players[1].cards.toString())
        assertEquals("2클로버", players[2].cards.toString())
    }

    @Test
    fun `getNames 메서드 호출시 player 닉네임 리스트를 반환한다`() {
        // given
        val players = Players(
            listOf(
                Player("player1"),
                Player("player2"),
                Player("player3")
            )
        )
        players.receiveCards(
            listOf(
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.HEARTS, Denomination.TEN),
                Card(Suit.CLUBS, Denomination.TWO),
            )
        )
        val expected = listOf("player1", "player2", "player3")

        // when
        val playerNames = players.getNames()

        // then
        assertEquals(expected, playerNames)
    }


    @Test
    fun `플레이어 중 hit 의사결정을 내린 사람들만 필터링한다`() {
        // given
        val players = Players(
            listOf(
                Player("player1"),
                Player("player2"),
                Player("player3")
            )
        )

        // when
        players[0].turnStand()

        // then
        assertEquals(2, players.withHit().size)
    }
}

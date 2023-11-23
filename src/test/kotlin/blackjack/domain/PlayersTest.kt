package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `player들의 이름과 받은 카드 정보를 반환한다`() {
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
        val expected = mapOf(
            "player1" to listOf(
                Card(Suit.SPADES, Denomination.ACE)
            ),
            "player2" to listOf(
                Card(Suit.HEARTS, Denomination.TEN)
            ),
            "player3" to listOf(
                Card(Suit.CLUBS, Denomination.TWO)
            )
        )

        // when
        val playerNamesAndCards = players.getPlayerNamesAndCards()

        // then
        assertEquals(expected, playerNamesAndCards)
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
        players.values[0].turnStand()

        // then
        assertEquals(2, players.withHit().size)
    }
}

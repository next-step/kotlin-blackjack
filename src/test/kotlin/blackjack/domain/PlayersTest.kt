//package blackjack.domain
//
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//
//class PlayersTest {
//    @Test
//    fun `getNames 메서드 호출시 player 닉네임 리스트를 반환한다`() {
//        // given
//        val players = Players(
//            listOf(
//                Player("player1"),
//                Player("player2"),
//                Player("player3")
//            )
//        )
//        val expected = listOf("player1", "player2", "player3")
//
//        // when
//        val playerNames = players.getNames()
//
//        // then
//        assertEquals(expected, playerNames)
//    }
//
//    @Test
//    fun `Hit 상태인 플레이어들만 필터링한다`() {
//        // given
//        val player1 = Player("player1")
//        player1.receiveInitialCards(
//            listOf(
//                Card(Suit.SPADES, Denomination.TEN),
//                Card(Suit.SPADES, Denomination.SIX),
//            )
//        )
//        player1
//        player1.turnStand()
//        val players = Players(
//            listOf(
//                Player("player1"),
//                Player("player2"),
//                Player("player3")
//            )
//        )
//
//        // when
//        players[0].turnStand()
//
//        // then
//        assertEquals(2, players.withHit().size)
//    }
//}

package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun player_win() {
        val players = Players(listOf("joohan"))
        val player = players.players[0]
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.NINE))

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo(WinOrLose.WIN)
        assertThat(result.dealerResult[WinOrLose.LOSE]).isEqualTo(1)
    }

    @Test
    fun player_lose() {
        val players = Players(listOf("joohan"))
        val player = players.players[0]
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.NINE))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo(WinOrLose.LOSE)
        assertThat(result.dealerResult[WinOrLose.WIN]).isEqualTo(1)
    }

    @Test
    fun player_is_bust() {
        val players = Players(listOf("joohan"))
        val player = players.players[0]
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo(WinOrLose.LOSE)
        assertThat(result.dealerResult[WinOrLose.WIN]).isEqualTo(1)
    }

    @Test
    fun dealer_is_bust() {
        val players = Players(listOf("joohan"))
        val player = players.players[0]
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo(WinOrLose.WIN)
        assertThat(result.dealerResult[WinOrLose.LOSE]).isEqualTo(1)
    }

    @Test
    fun player_dealer_is_bust_draw() {
        val players = Players(listOf("joohan"))
        val player = players.players[0]
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo(WinOrLose.LOSE)
        assertThat(result.dealerResult[WinOrLose.WIN]).isEqualTo(1)
    }

    @Test
    fun draw() {
        val players = Players(listOf("joohan"))
        val player = players.players[0]
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo(WinOrLose.DRAW)
        assertThat(result.dealerResult[WinOrLose.DRAW]).isEqualTo(1)
    }
}

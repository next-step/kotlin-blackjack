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
        DealerResult.resetResult()

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo("승")
        assertThat(DealerResult.LOSE.getCount()).isEqualTo(1)
    }

    @Test
    fun player_lose() {
        val players = Players(listOf("joohan"))
        val player = players.players[0]
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.NINE))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        DealerResult.resetResult()

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo("패")
        assertThat(DealerResult.WIN.getCount()).isEqualTo(1)
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
        DealerResult.resetResult()

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo("패")
        assertThat(DealerResult.WIN.getCount()).isEqualTo(1)
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
        DealerResult.resetResult()

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo("승")
        assertThat(DealerResult.LOSE.getCount()).isEqualTo(1)
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
        DealerResult.resetResult()

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo("패")
        assertThat(DealerResult.WIN.getCount()).isEqualTo(1)
    }

    @Test
    fun draw() {
        val players = Players(listOf("joohan"))
        val player = players.players[0]
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        DealerResult.resetResult()

        val result = Result(dealer, players)

        assertThat(result.playersResult[player]).isEqualTo("무")
        assertThat(DealerResult.DRAW.getCount()).isEqualTo(1)
    }
}

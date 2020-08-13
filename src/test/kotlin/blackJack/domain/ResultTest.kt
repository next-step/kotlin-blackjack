package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun player_win() {
        val player = Player("joohan")
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.NINE))

        val result = Result(dealer)

        assertThat(result.get(player)).isEqualTo("승")
        assertThat(result.dealerResult["패"]).isEqualTo(1)
    }

    @Test
    fun player_lose() {
        val player = Player("joohan")
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.NINE))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer)

        assertThat(result.get(player)).isEqualTo("패")
        assertThat(result.dealerResult["승"]).isEqualTo(1)
    }

    @Test
    fun player_is_bust() {
        val player = Player("joohan")
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer)

        assertThat(result.get(player)).isEqualTo("패")
        assertThat(result.dealerResult["승"]).isEqualTo(1)
    }

    @Test
    fun dealer_is_bust() {
        val player = Player("joohan")
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer)

        assertThat(result.get(player)).isEqualTo("승")
        assertThat(result.dealerResult["패"]).isEqualTo(1)
    }

    @Test
    fun player_dealer_is_bust_draw() {
        val player = Player("joohan")
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer)

        assertThat(result.get(player)).isEqualTo("패")
        assertThat(result.dealerResult["승"]).isEqualTo(1)
    }

    @Test
    fun draw() {
        val player = Player("joohan")
        val dealer = Dealer()
        player.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        val result = Result(dealer)

        assertThat(result.get(player)).isEqualTo("무")
        assertThat(result.dealerResult["무"]).isEqualTo(1)
    }
}

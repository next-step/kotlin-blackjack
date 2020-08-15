package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RevenueTest {

    @Test
    fun `A와 B가 배팅금을 각각 만원, 2만원씩 걸고 A가 이기고 B가 졌을 경우`() {
        val dealer = Dealer().apply {
            requestCard(Card(Denomination.THREE to Shape.DIAMOND))
            requestCard(Card(Denomination.NINE to Shape.CLUB))
            requestCard(Card(Denomination.EIGHT to Shape.DIAMOND))
        }
        val players = listOf("moshi", "gson").map(::Player)
        players[0].run {
            requestCard(Card(Denomination.TWO to Shape.HEART))
            requestCard(Card(Denomination.EIGHT to Shape.SPADE))
            requestCard(Card(Denomination.ACE to Shape.CLUB))
        }
        players[1].run {
            requestCard(Card(Denomination.SEVEN to Shape.CLUB))
            requestCard(Card(Denomination.KING to Shape.SPADE))
        }
        val game = BlackJackGame(dealer, players)
        val playersBettingMoney = listOf(10_000, 20_000)

        val revenue = Revenue.getRevenue(game, playersBettingMoney)

        assertThat(revenue).isEqualTo(listOf(10_000, 10_000, -20_000))
    }

    @Test
    fun `모두 블랙잭인 경우`() {
        val dealer = Dealer().apply {
            requestCard(Card(Denomination.ACE to Shape.DIAMOND))
            requestCard(Card(Denomination.KING to Shape.CLUB))
        }
        val players = listOf("moshi", "gson").map(::Player)
        players[0].run {
            requestCard(Card(Denomination.ACE to Shape.HEART))
            requestCard(Card(Denomination.QUEEN to Shape.SPADE))
        }
        players[1].run {
            requestCard(Card(Denomination.ACE to Shape.CLUB))
            requestCard(Card(Denomination.JACK to Shape.SPADE))
        }
        val game = BlackJackGame(dealer, players)
        val playersBettingMoney = listOf(10_000, 10_000)

        val revenue = Revenue.getRevenue(game, playersBettingMoney)

        assertThat(revenue).isEqualTo(listOf(0, 0, 0))
    }

    @Test
    fun `A가 블랙잭인 경우 1_5배 받기`() {
        val dealer = Dealer().apply {
            requestCard(Card(Denomination.THREE to Shape.DIAMOND))
            requestCard(Card(Denomination.NINE to Shape.CLUB))
            requestCard(Card(Denomination.EIGHT to Shape.DIAMOND))
        }
        val players = listOf("moshi", "gson").map(::Player)
        players[0].run {
            requestCard(Card(Denomination.JACK to Shape.SPADE))
            requestCard(Card(Denomination.ACE to Shape.CLUB))
        }
        players[1].run {
            requestCard(Card(Denomination.SEVEN to Shape.CLUB))
            requestCard(Card(Denomination.KING to Shape.SPADE))
        }

        val game = BlackJackGame(dealer, players)
        val playersBettingMoney = listOf(10_000, 20_000)

        val revenue = Revenue.getRevenue(game, playersBettingMoney)

        assertThat(revenue).isEqualTo(listOf(5_000, 15_000, -20_000))
    }

    @Test
    fun `딜러가 21을 초과할 경우 배팅금 돌려 받기`() {
        val dealer = Dealer().apply {
            requestCard(Card(Denomination.THREE to Shape.DIAMOND))
            requestCard(Card(Denomination.NINE to Shape.CLUB))
            requestCard(Card(Denomination.TEN to Shape.DIAMOND))
        }
        val players = listOf("moshi", "gson").map(::Player)
        players[0].run {
            requestCard(Card(Denomination.JACK to Shape.SPADE))
            requestCard(Card(Denomination.JACK to Shape.CLUB))
        }
        players[1].run {
            requestCard(Card(Denomination.SEVEN to Shape.CLUB))
            requestCard(Card(Denomination.KING to Shape.SPADE))
        }

        val game = BlackJackGame(dealer, players)
        val playersBettingMoney = listOf(10_000, 20_000)

        val revenue = Revenue.getRevenue(game, playersBettingMoney)

        assertThat(revenue).isEqualTo(listOf(-30_000, 10_000, 20_000))
    }
}

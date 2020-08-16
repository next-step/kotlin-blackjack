package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RevenueTest {

    @Test
    fun `A와 B가 배팅금을 각각 만원, 2만원씩 걸고 A가 이기고 B가 졌을 경우`() {
        val dealer = Dealer().apply {
            requestCard(Card(Denomination.THREE, Shape.DIAMOND))
            requestCard(Card(Denomination.NINE, Shape.CLUB))
            requestCard(Card(Denomination.EIGHT, Shape.DIAMOND))
        }
        val players = listOf("moshi", "gson").map(::Player)
        players[0].run {
            requestCard(Card(Denomination.TWO, Shape.HEART))
            requestCard(Card(Denomination.EIGHT, Shape.SPADE))
            requestCard(Card(Denomination.ACE, Shape.CLUB))
        }
        players[1].run {
            requestCard(Card(Denomination.SEVEN, Shape.CLUB))
            requestCard(Card(Denomination.KING, Shape.SPADE))
        }
        val game = BlackJackGame(dealer, players)
        val playersBettingMoney = listOf(10_000, 20_000)

        val revenue = Revenue.get(game, playersBettingMoney)

        assertThat(revenue).isEqualTo(listOf(10_000, 10_000, -20_000))
    }

    @Test
    fun `모두 블랙잭인 경우`() {
        val dealer = Dealer().apply {
            requestCard(Card(Denomination.ACE, Shape.DIAMOND))
            requestCard(Card(Denomination.KING, Shape.CLUB))
        }
        val players = listOf("moshi", "gson").map(::Player)
        players[0].run {
            requestCard(Card(Denomination.ACE, Shape.HEART))
            requestCard(Card(Denomination.QUEEN, Shape.SPADE))
        }
        players[1].run {
            requestCard(Card(Denomination.ACE, Shape.CLUB))
            requestCard(Card(Denomination.JACK, Shape.SPADE))
        }
        val game = BlackJackGame(dealer, players)
        val playersBettingMoney = listOf(10_000, 10_000)

        val revenue = Revenue.get(game, playersBettingMoney)

        assertThat(revenue).isEqualTo(listOf(0, 0, 0))
    }

    @Test
    fun `A가 블랙잭인 경우 1_5배 받기`() {
        val dealer = Dealer().apply {
            requestCard(Card(Denomination.THREE, Shape.DIAMOND))
            requestCard(Card(Denomination.NINE, Shape.CLUB))
            requestCard(Card(Denomination.EIGHT, Shape.DIAMOND))
        }
        val players = listOf("moshi", "gson").map(::Player)
        players[0].run {
            requestCard(Card(Denomination.JACK, Shape.SPADE))
            requestCard(Card(Denomination.ACE, Shape.CLUB))
        }
        players[1].run {
            requestCard(Card(Denomination.SEVEN, Shape.CLUB))
            requestCard(Card(Denomination.KING, Shape.SPADE))
        }

        val game = BlackJackGame(dealer, players)
        val playersBettingMoney = listOf(10_000, 20_000)

        val revenue = Revenue.get(game, playersBettingMoney)

        assertThat(revenue).isEqualTo(listOf(5_000, 15_000, -20_000))
    }

    @Test
    fun `딜러가 21을 초과할 경우 배팅금 돌려 받기`() {
        val dealer = Dealer().apply {
            requestCard(Card(Denomination.THREE, Shape.DIAMOND))
            requestCard(Card(Denomination.NINE, Shape.CLUB))
            requestCard(Card(Denomination.TEN, Shape.DIAMOND))
        }
        val players = listOf("moshi", "gson").map(::Player)
        players[0].run {
            requestCard(Card(Denomination.JACK, Shape.SPADE))
            requestCard(Card(Denomination.JACK, Shape.CLUB))
        }
        players[1].run {
            requestCard(Card(Denomination.SEVEN, Shape.CLUB))
            requestCard(Card(Denomination.KING, Shape.SPADE))
        }

        val game = BlackJackGame(dealer, players)
        val playersBettingMoney = listOf(10_000, 20_000)

        val revenue = Revenue.get(game, playersBettingMoney)

        assertThat(revenue).isEqualTo(listOf(-30_000, 10_000, 20_000))
    }
}

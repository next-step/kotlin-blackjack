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
}

object Revenue {

    private const val BLACK_JACK_REVENUE = 1.5

    fun getRevenue(game: BlackJackGame, playersBettingMoney: List<Int>): List<Int> {
        val (dealerPoint, playersPoint) = game.getDealerPoint() to game.getPlayersPoint()
        val playersIsBlackJackState = game.getPlayersBlackJackState()
        val playersWinOrNot = playersPoint.zip(playersIsBlackJackState).map {
            when {
                dealerPoint > Point.MAX_POINT -> Score(isWin = true, isBlackJack = it.second)
                it.first > Point.MAX_POINT -> Score()
                dealerPoint == Point.MAX_POINT && it.first == Point.MAX_POINT ->
                    Score(isDraw = true, isBlackJack = it.second)
                else -> Score(isWin = dealerPoint < it.first, isBlackJack = it.second)
            }
        }
        val playersRevenue = playersWinOrNot
            .zip(playersBettingMoney)
            .map {
                when {
                    it.first.isBlackJack -> (it.second * BLACK_JACK_REVENUE).toInt()
                    it.first.isWin -> it.second
                    it.first.isDraw -> 0
                    else -> -it.second
                }
            }
        val dealerRevenue = -playersRevenue.reduce { acc, money -> acc + money }
        return listOf(listOf(dealerRevenue), playersRevenue).flatten()
    }
}

data class Score(
    val isWin: Boolean = false,
    val isDraw: Boolean = false,
    val isBlackJack: Boolean = false
)

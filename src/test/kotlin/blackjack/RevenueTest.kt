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
}

object Revenue {

    fun getRevenue(game: BlackJackGame, playersBettingMoney: List<Int>): List<Int> {
        val (dealerPoint, playersPoint) = game.getDealerPoint() to game.getPlayersPoint()
        val playersWinOrNot = playersPoint.map { playerPoint ->
            when {
                dealerPoint > Point.MAX_POINT -> Score(isWin = true)
                playerPoint > Point.MAX_POINT -> Score()
                dealerPoint == Point.MAX_POINT && playerPoint == Point.MAX_POINT -> Score(isDraw = true)
                else -> Score(isWin = dealerPoint < playerPoint)
            }
        }
        val playersRevenue = playersWinOrNot
            .zip(playersBettingMoney)
            .map {
                when {
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
    val isDraw: Boolean = false
)

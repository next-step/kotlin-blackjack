package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RevenueTest {

    @Test
    fun `A와 B가 배팅금을 각각 만원, 2만원씩 걸고 A가 이기고 B가 졌을 경우`() {
        val players = listOf(
            Score(isWin = true, bettingMoney = 10_000),
            Score(bettingMoney = 20_000)
        )
        val playersRevenue = Revenue.getPlayersRevenue(players)
        val dealersRevenue = Revenue.getDealerRevenue(playersRevenue)
        val revenue = mutableListOf<Int>().apply {
            add(dealersRevenue)
            addAll(playersRevenue)
        }

        assertThat(revenue).isEqualTo(listOf(10_000, 10_000, -20_000))
    }

    @Test
    fun `모두 블랙잭인 경우`() {
        val players = listOf(
            Score(isBlackJack = true),
            Score(isBlackJack = true)
        )
        val playersRevenue = Revenue.getPlayersRevenue(players)
        val dealersRevenue = Revenue.getDealerRevenue(playersRevenue)
        val revenue = mutableListOf<Int>().apply {
            add(dealersRevenue)
            addAll(playersRevenue)
        }

        assertThat(revenue).isEqualTo(listOf(0, 0, 0))
    }

    @Test
    fun `A가 블랙잭인 경우 1_5배 받기`() {
        val players = listOf(
            Score(isBlackJack = true, bettingMoney = 10_000),
            Score(bettingMoney = 20_000)
        )
        val playersRevenue = Revenue.getPlayersRevenue(players)
        val dealersRevenue = Revenue.getDealerRevenue(playersRevenue)
        val revenue = mutableListOf<Int>().apply {
            add(dealersRevenue)
            addAll(playersRevenue)
        }

        assertThat(revenue).isEqualTo(listOf(5_000, 15_000, -20_000))
    }

    @Test
    fun `딜러가 21을 초과할 경우 배팅금 돌려 받기`() {
        val players = listOf(
            Score(isWin = true, bettingMoney = 10_000),
            Score(isWin = true, bettingMoney = 20_000)
        )
        val playersRevenue = Revenue.getPlayersRevenue(players)
        val dealersRevenue = Revenue.getDealerRevenue(playersRevenue)
        val revenue = mutableListOf<Int>().apply {
            add(dealersRevenue)
            addAll(playersRevenue)
        }

        assertThat(revenue).isEqualTo(listOf(-30_000, 10_000, 20_000))
    }
}

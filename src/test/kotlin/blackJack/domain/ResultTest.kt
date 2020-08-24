package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun make_result() {
        val players = Players(listOf("joo", "han"))
        val dealer = Dealer()

        val result = Result(players, dealer)

        assertThat(result.dealerProfit).isEqualTo(0)
        assertThat(result.playersProfit).isNotEmpty()
    }

    @Test
    fun get_dealer_result_and_player_result() {
        val players = Players(listOf("joo", "han", "park"))
        players.players[0].addCard(SPADE_KING)
        players.players[1].addCard(SPADE_SIX)
        players.players[2].addCard(SPADE_NINE)
        players.players[0].bettingMoney(1000)
        players.players[1].bettingMoney(1000)
        players.players[2].bettingMoney(1000)
        val dealer = Dealer()
        dealer.addCard(SPADE_NINE)

        val result = Result(players, dealer)

        assertThat(result.dealerProfit).isEqualTo(0)
        assertThat(result.playersProfit[players.players[0]]).isEqualTo(1000)
        assertThat(result.playersProfit[players.players[1]]).isEqualTo(-1000)
        assertThat(result.playersProfit[players.players[2]]).isEqualTo(0)
    }
}

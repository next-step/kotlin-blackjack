package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun make_player() {
        val player = Player("joohan")

        assertThat(player.name).isEqualTo("joohan")
        assertThat(player.hands).hasSize(0)
        assertThat(player.betMoney).isEqualTo(0)
    }

    @Test
    fun betting_money() {
        val player = Player("joohan")

        player.bettingMoney(1000)

        assertThat(player.betMoney).isEqualTo(1000)
    }

    @Test
    fun get_profit() {
        val player = Player("joohan")
        player.bettingMoney(1000)

        val winResult = player.getProfit(WinOrLose.WIN)
        val drawResult = player.getProfit(WinOrLose.DRAW)
        val loseResult = player.getProfit(WinOrLose.LOSE)

        assertThat(winResult).isEqualTo(1000)
        assertThat(drawResult).isEqualTo(0)
        assertThat(loseResult).isEqualTo(-1000)
    }

    @Test
    fun get_profit_blackJack() {
        val player = Player("joohan")
        player.bettingMoney(1000)
        player.addCard(SPADE_JACK)
        player.addCard(SPADE_ACE)

        val blackJackResult = player.getProfit(WinOrLose.WIN)

        assertThat(blackJackResult).isEqualTo(1500)
    }

    @Test
    fun is_bust() {
        val player = Player("joohan")
        player.addCard(SPADE_NINE)
        player.addCard(SPADE_KING)
        player.addCard(SPADE_JACK)

        assertThat(player.isBust()).isTrue()
    }

    @Test
    fun player_can_not_add_card_over21() {
        val player = Player("joo")
        player.addCard(SPADE_NINE)
        player.addCard(SPADE_KING)
        player.addCard(SPADE_JACK)

        assertThatThrownBy {
            player.addCard(SPADE_JACK)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("카드의 총합이 21점을 넘겼기 때문에 카드를 더 받을수 없습니다.")
    }
}

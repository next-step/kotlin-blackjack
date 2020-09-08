package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PlayerTest {
    private val blackJackPlayer = Player("blackJack")
    private val bustPlayer = Player("bust")
    init {
        blackJackPlayer.addCard(SPADE_ACE)
        blackJackPlayer.addCard(SPADE_JACK)
        bustPlayer.addCard(SPADE_KING)
        bustPlayer.addCard(SPADE_KING)
        bustPlayer.addCard(SPADE_KING)
    }

    @Test
    fun make_player() {
        val player = Player("joohan")

        assertThat(player.name).isEqualTo("joohan")
        assertThat(player.hands.cards).hasSize(0)
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
        val player = blackJackPlayer
        player.bettingMoney(1000)

        val blackJackResult = player.getProfit(WinOrLose.WIN)

        assertThat(blackJackResult).isEqualTo(1500)
    }

    @Test
    fun is_bust() {
        val player = bustPlayer

        assertThat(player.isBust()).isTrue()
    }

    @Test
    fun player_can_not_add_card_over21() {
        val player = bustPlayer

        assertThatThrownBy {
            player.addCard(SPADE_JACK)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("카드의 총합이 21점을 넘겼기 때문에 카드를 더 받을수 없습니다.")
    }
}

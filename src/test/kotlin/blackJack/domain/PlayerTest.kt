package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
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
}

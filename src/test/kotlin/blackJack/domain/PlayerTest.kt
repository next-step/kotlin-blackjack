package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun make_player() {
        val player = Player("joohan")

        assertThat(player.name).isEqualTo("joohan")
        assertThat(player.hands.size).isEqualTo(0)
    }

    @Test
    fun give_card() {
        val player = Player("joohan")

        player.giveCard(Card("♠10", 10))

        assertThat(player.hands.size).isEqualTo(1)
        assertThat(player.hands[0]).isEqualTo(Card("♠10", 10))
    }
}

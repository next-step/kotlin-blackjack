package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun make_player() {
        val player = Player("joohan")

        assertThat(player.name).isEqualTo("joohan")
        assertThat(player.hands).hasSize(0)
    }
}

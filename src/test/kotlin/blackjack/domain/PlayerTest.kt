package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `Player는 이름을 가진다`() {
        val name = "han"

        val player = Player(name)

        assertThat(player.name).isEqualTo(name)
    }
}

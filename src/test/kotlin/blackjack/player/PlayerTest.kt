package blackjack.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class PlayerTest {
    @Test
    internal fun `플레이어는 이름을 받아 생성된다`() {
        assertDoesNotThrow { Player(name = "Bryce Carlson") }
    }

    @Test
    internal fun `플레이어는 생성될 때의 이름을 그대로 가지고 있다`() {
        // given
        val name = "Edward O. Thorp"

        // when
        val player = Player(name)

        // then
        assertThat(player.name).isEqualTo(name)
    }
}

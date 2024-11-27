package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player("이름1")
        assertThat(player.name).isEqualTo("이름1")
    }
}

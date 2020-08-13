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
    }

    @Test
    fun player_name_is_blank() {
        assertThatThrownBy {
            Player("")
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessageContaining("이름은 없을수 받을수 없습니다.")
    }
}

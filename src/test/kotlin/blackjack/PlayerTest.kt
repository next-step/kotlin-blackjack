package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어 이름 출력하기`() {
        val player = Player("moshi")

        assertThat(player.toString()).isEqualTo("moshi")
    }
}

class Player(private val name: String) {

    override fun toString(): String = name
}

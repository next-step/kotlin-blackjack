package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player("이름")
        Assertions.assertThat(player.name).isEqualTo("이름")
    }

}
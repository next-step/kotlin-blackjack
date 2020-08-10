package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun make_player() {
        val nameList = listOf("joohan", "pikachu")

        val blackJack = BlackJack(nameList)

        assertThat(blackJack.players.size).isEqualTo(2)
    }
}

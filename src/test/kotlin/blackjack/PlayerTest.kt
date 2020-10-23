package blackjack

import blackjack.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

val sampleplayer = Player("mark")

class PlayerTest {

    @Test
    fun `이름 출력`() {
        assertThat(sampleplayer.toString()).isEqualTo("mark")
    }
}
package blackjack.domain.state

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BustTest {

    @Test
    fun isFinish() {
        val bust = Bust()
        assertThat(bust.isFinished).isTrue()
    }
}
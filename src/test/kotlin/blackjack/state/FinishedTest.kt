package blackjack.state

import blackjack.model.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class FinishedTest {

    @Test
    fun `Finished 상태는 다른 상태로 변환이 불가능하다`() {
        assertAll(
            { assertThat(Bust(Cards.empty()).stay()).isInstanceOf(Bust::class.java) },
            { assertThat(Stay(Cards.empty()).stay()).isInstanceOf(Stay::class.java) },
            { assertThat(Blackjack(Cards.empty()).stay()).isInstanceOf(Blackjack::class.java) },
        )
    }
}

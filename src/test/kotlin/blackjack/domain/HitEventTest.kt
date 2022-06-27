package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitEventTest {
    @Test
    fun `observe를 통해 Observer를 HitEvent에 등록할 수 있다`() {
        val hitEvent = HitEvent()
        val player = Player(
            "마리오",
            10_000,
            PlayingCard(Suit.DIAMONDS, CardNumber.NINE),
            PlayingCard(Suit.DIAMONDS, CardNumber.SEVEN)
        )
        hitEvent.emit(player)
        hitEvent.observe { participant ->
            assertThat(participant).isEqualTo(player)
        }
    }
}

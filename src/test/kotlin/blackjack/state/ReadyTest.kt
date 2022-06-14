package blackjack.state

import blackjack.PlayerDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReadyTest {
    @Test
    fun `Ready 상태의 현재 가지고 있는 카드가 비었는지 확인`() {
        val ready = Ready(PlayerDeck())
        assertThat(ready.currentCard().cards).isEmpty()
    }

    @Test
    fun `Ready 상태에서 카드를 더 받을 수 있다`() {
        val ready = Ready(PlayerDeck())
        assertThat(ready.isFinish()).isFalse
    }
}

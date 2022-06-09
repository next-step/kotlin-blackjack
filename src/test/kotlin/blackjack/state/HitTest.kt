package blackjack.state

import blackjack.Card
import blackjack.CardNumber
import blackjack.CardShape
import blackjack.PlayerDeck
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class HitTest {
    @Test
    fun `Hit 상태의 현재 가지고 있는 카드가 2장 미만이라면 예외처리`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Hit(PlayerDeck()) }
    }

    @Test
    fun `Hit 상태에서 카드를 더 받을 수 있다`() {
        val hit = Hit(
            PlayerDeck(
                listOf(
                    Card(CardShape.CLOVER, CardNumber(10)),
                    Card(CardShape.HEART, CardNumber(10))
                )
            )
        )
        assertThat(hit.isFinish()).isFalse
    }
}

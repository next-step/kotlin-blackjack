package blackjack.state

import blackjack.Card
import blackjack.CardNumber
import blackjack.CardShape
import blackjack.PlayerDeck
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class BurstTest {

    @Test
    fun `Burst 상태의 현재 가지고 있는 카드의 점수가 21이하라면 예외처리`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Burst(PlayerDeck()) }
    }

    @Test
    fun `Burst 상태에서 카드를 더 받을 수 없다`() {
        val burst = Burst(
            PlayerDeck(
                listOf(
                    Card(CardShape.CLOVER, CardNumber(10)),
                    Card(CardShape.HEART, CardNumber(10)),
                    Card(CardShape.DIAMOND, CardNumber(10))
                )
            )
        )
        assertThat(burst.isFinish()).isTrue
    }
}

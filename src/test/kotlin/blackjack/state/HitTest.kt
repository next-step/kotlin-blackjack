package blackjack.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardSymbol
import blackjack.domain.card.PlayerDeck
import blackjack.domain.state.Hit
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
                    Card(CardShape.CLOVER, CardSymbol.JACK),
                    Card(CardShape.HEART, CardSymbol.JACK)
                )
            )
        )
        assertThat(hit.isFinish()).isFalse
    }
}

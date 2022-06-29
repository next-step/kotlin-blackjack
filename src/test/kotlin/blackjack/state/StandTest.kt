package blackjack.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardSymbol
import blackjack.domain.card.PlayerDeck
import blackjack.domain.state.Stand
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class StandTest {
    @Test
    fun `Stand 상태의 카드를 더 받을 시 예외처리`() {
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { Stand(PlayerDeck()).draw(Card(CardShape.CLOVER, CardSymbol.ACE)) }
    }

    @Test
    fun `Stand 상태에서 카드를 더 받을 수 없다`() {
        val stand = Stand(
            PlayerDeck(
                listOf(
                    Card(CardShape.CLOVER, CardSymbol.JACK),
                    Card(CardShape.HEART, CardSymbol.JACK),
                    Card(CardShape.DIAMOND, CardSymbol.JACK)
                )
            )
        )
        Assertions.assertThat(stand.isFinish()).isTrue
    }
}

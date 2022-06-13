package blackjack.state

import blackjack.Card
import blackjack.CardShape
import blackjack.CardSymbol
import blackjack.PlayerDeck
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class BlackJackTest {

    @Test
    fun `Blackjack 의 카드의 조건에 맞지 않으면 예외처리`() {
        assertThatIllegalArgumentException()
            .isThrownBy { BlackJack(PlayerDeck()) }
    }

    @Test
    fun `Blackjack 상태에서 카드를 더 받을 수 없다`() {
        val blackJack = BlackJack(
            PlayerDeck(
                listOf(
                    Card(CardShape.CLOVER, CardSymbol.JACK),
                    Card(CardShape.HEART, CardSymbol.ACE)
                )
            )
        )
        assertThat(blackJack.isFinish()).isTrue
    }
}

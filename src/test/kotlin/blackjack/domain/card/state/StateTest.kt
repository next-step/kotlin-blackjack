package blackjack.domain.card.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class StateTest {

    private val ace = Card(Suit.CLUB, Denomination.ACE)
    private val ten = Card(Suit.CLUB, Denomination.TEN)

    @Test
    fun `카드 점수가 21점 일 경우 state는 blackjack`() {
        val state = StateFactory.create(ace, ten)

        assertThat(state is BlackJack).isTrue()
        assertThat(state.isFinished()).isTrue()
        assertThrows<IllegalStateException> { state.draw(ace) }
    }

    @Test
    fun `카드 점수가 21점 보다 클 경우 state는 bust`() {
        val state = StateFactory.create(ten, ten)
        val bust = state.draw(ten)

        assertThat(bust is Bust).isTrue()
        assertThat(bust.isFinished()).isTrue()
        assertThrows<IllegalStateException> { bust.draw(ace) }
    }

    @Test
    fun `카드 점수가 21점 보다 작을 경우 state는 hit`() {
        val state = StateFactory.create(ten, ten)

        assertThat(state is Hit).isTrue()
        assertThat(state.isFinished()).isFalse()
        assertDoesNotThrow { state.draw(ace) }
    }
}

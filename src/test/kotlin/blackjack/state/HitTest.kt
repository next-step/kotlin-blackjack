package blackjack.state

import blackjack.model.Card
import blackjack.model.Denomination
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HitTest {

    private lateinit var state: State

    @BeforeEach
    fun setup() {
        state = Ready()
            .draw(Card(Denomination.FIVE, Suit.CLOVER))
            .draw(Card(Denomination.TEN, Suit.CLOVER))
    }

    @Test
    fun `카드 한 장을 뽑았을 때 21을 초과하면 Bust 상태가 된다`() {
        val actual = state.draw(Card(Denomination.TEN, Suit.HEART))
        assertThat(actual).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `카드 한 장을 뽑았을 때 21보다 작다면 Hit 상태가 된다`() {
        val actual = state.draw(Card(Denomination.TWO, Suit.HEART))
        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Stay 상태로 전환이 가능하다`() {
        assertThat(state.stay()).isInstanceOf(Stay::class.java)
    }
}

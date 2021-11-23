package blackjack.state

import blackjack.model.Card
import blackjack.model.Denomination
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReadyTest {

    @Test
    fun `카드 한 장을 뽑으면 Ready 상태가 된다`() {
        val actual = Ready().draw(Card(Denomination.FIVE, Suit.CLOVER))
        assertThat(actual).isInstanceOf(Ready::class.java)
    }

    @Test
    fun `카드 두 장을 뽑았을 때 21보다 작으면 Hit 상태가 된다`() {
        val actual = Ready()
            .draw(Card(Denomination.FIVE, Suit.CLOVER))
            .draw(Card(Denomination.TEN, Suit.CLOVER))
        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `카드 두 장을 뽑았을 때 21이면 Blackjack 상태가 된다`() {
        val actual = Ready()
            .draw(Card(Denomination.ACE, Suit.CLOVER))
            .draw(Card(Denomination.TEN, Suit.CLOVER))
        assertThat(actual).isInstanceOf(Blackjack::class.java)
    }
}

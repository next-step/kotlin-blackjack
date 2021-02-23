package blackjack.domain.player

import blackjack.domain.deck.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class StartTest {
    @Test
    fun `draw한 값이 21이면 블랙잭`() {
        val cards = Stack<Card>()
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.ACE, Suit.CLOVER))
        val deck = Deck(cards)
        val start = Start(Cards(listOf()))

        val nextState = start.draw(deck)

        assertThat(nextState is Blackjack).isTrue()
    }

    @Test
    fun `draw한 값이 21이 아니면 Hit`() {
        val cards = Stack<Card>()
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        val deck = Deck(cards)
        val start = Start(Cards(listOf()))

        val nextState = start.draw(deck)

        assertThat(nextState is Hit).isTrue()
    }

    @Test
    fun `stay 불가`() {
        val start = Start(Cards(listOf()))

        assertThrows<IllegalArgumentException> {
            start.stay()
        }
    }

    @Test
    fun `아직 종료되지 않은 상태 확인`() {
        val start = Start(Cards(listOf()))

        assertThat(start.isFinished()).isFalse()
    }

    @Test
    fun `계산 0`() {
        val start = Start(Cards(listOf()))

        assertThat(start.calculate()).isEqualTo(0)
    }
}

package blackjack.domain.player

import blackjack.domain.deck.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

internal class HitTest {
    @Test
    fun `draw결과가 21초과면 BUST`() {
        val cards = Stack<Card>()
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        val deck = Deck(cards)
        val hit =
            Hit(Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.TEN, Suit.CLOVER))))
        val bust = hit.draw(deck)

        assertThat(bust is Bust).isTrue()
    }

    @Test
    fun `draw결과가 21초과가 아니라면 Hit`() {
        val cards = Stack<Card>()
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        val deck = Deck(cards)
        val hit =
            Hit(Cards(listOf(Card(Denomination.NINE, Suit.CLOVER), Card(Denomination.ACE, Suit.CLOVER))))
        val bust = hit.draw(deck)

        assertThat(bust is Hit).isTrue()
    }

    @Test
    fun `뽑기를 그만하면 Stay`() {
        val hit =
            Hit(Cards(listOf(Card(Denomination.NINE, Suit.CLOVER), Card(Denomination.ACE, Suit.CLOVER))))
        val stay = hit.stay()

        assertThat(stay is Stay).isTrue()
    }

    @Test
    fun `아직 종료상태가 아님`() {
        val hit =
            Hit(Cards(listOf(Card(Denomination.NINE, Suit.CLOVER), Card(Denomination.ACE, Suit.CLOVER))))

        assertThat(hit.isFinished()).isFalse()
    }

    @Test
    fun `계산 테스트`() {
        val hit =
            Hit(Cards(listOf(Card(Denomination.NINE, Suit.CLOVER), Card(Denomination.ACE, Suit.CLOVER))))

        assertThat(hit.calculate()).isEqualTo(20)
    }
}

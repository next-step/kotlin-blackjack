package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {

    @Test
    fun `중복된 카드가 있으면 RuntimeException 예외가 발생한다`() {
        assertThrows<RuntimeException> {
            Cards(
                listOf(
                    Card(Denomination.ACE, Suit.CLOVER),
                    Card(Denomination.ACE, Suit.CLOVER)
                )
            )
        }
    }

    @Test
    fun `카드를 추가할 수 있다`() {
        assertThat(
            Cards.empty()
                .add(Card(Denomination.ACE, Suit.CLOVER))
                .contains(Card(Denomination.ACE, Suit.CLOVER))
        ).isTrue
    }

    @Test
    fun `남아있는 카드 개수를 알 수 있다`() {
        assertThat(Cards.empty().size).isZero
    }

    @Test
    fun `가장 위에 있는 카드를 확인할 수 있다`() {
        assertThat(
            Cards.empty()
                .add(Card(Denomination.ACE, Suit.CLOVER))
                .peek()
        ).isEqualTo(Card(Denomination.ACE, Suit.CLOVER))
    }

    @Test
    fun `비어있는지 확인할 수 있다`() {
        assertThat(Cards.empty().isEmpty()).isTrue
    }

    @Test
    fun `가장 위에 있는 카드를 뽑을 수 있다`() {
        assertThat(
            Cards.empty()
                .add(Card(Denomination.ACE, Suit.CLOVER))
                .add(Card(Denomination.ACE, Suit.HEART))
                .draw()
                .peek()
        ).isEqualTo(Card(Denomination.ACE, Suit.HEART))
    }

    @Test
    fun `카드들의 숫자 합을 계산할 수 있다`() {
        assertThat(
            Cards.empty()
                .add(Card(Denomination.ACE, Suit.CLOVER))
                .add(Card(Denomination.ACE, Suit.HEART))
                .sum()
        ).isEqualTo(12)
    }
}

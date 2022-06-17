package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardDeckTest {
    @Test
    internal fun `distinct()로 카드 중복 확인 되는지 테스트`() {
        val cards = listOf(
            Card(Symbol.SPADE, CardNumber.ACE),
            Card(Symbol.SPADE, CardNumber.ACE)
        )
        assertThat(cards.distinct().size).isEqualTo(1)
    }

    @Test
    internal fun `카드가 중복이 되어서는 안된다`() {
        val cards = List(48) {
            CardDeck.getOne()
        }
        assertThat(cards.distinct().size).isEqualTo(48)
    }
}

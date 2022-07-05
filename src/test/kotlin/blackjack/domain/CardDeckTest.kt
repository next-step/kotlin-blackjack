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
        assertThat(cards.distinct()).hasSize(1)
    }

    @Test
    internal fun `카드가 중복이 되어서는 안된다`() {
        val cardDeck = CardDeck()
        val cards = mutableListOf<Card>()
        repeat(48) {
            cards.add(cardDeck.getOne())
        }
        assertThat(cards.distinct()).hasSize(cards.size)
        assertThat(cardDeck.isLeft).isFalse
    }

    @Test
    internal fun `남은 카드가 있는지 확인 - 있을 때`() {
        val cardDeck = CardDeck()
        cardDeck.getOne()
        assertThat(cardDeck.isLeft).isTrue
    }

    @Test
    internal fun `남은 카드가 있는지 확인 - 없을 때`() {
        val cardDeck = CardDeck()
        repeat(48) {
            cardDeck.getOne()
        }
        assertThat(cardDeck.isLeft).isFalse
    }
}

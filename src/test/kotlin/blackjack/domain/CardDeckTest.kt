package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
internal class CardDeckTest {
    @Test
    @Order(1)
    internal fun `distinct()로 카드 중복 확인 되는지 테스트`() {
        val cards = listOf(
            Card(Symbol.SPADE, CardNumber.ACE),
            Card(Symbol.SPADE, CardNumber.ACE)
        )
        assertThat(cards.distinct()).hasSize(1)
    }

    @Test
    @Order(3)
    internal fun `카드가 중복이 되어서는 안된다`() {
        val cards = mutableListOf<Card>()
        while (CardDeck.isLeft()) {
            cards.add(CardDeck.getOne())
        }
        assertThat(cards.distinct()).hasSize(cards.size)
        assertThat(CardDeck.isLeft()).isFalse
    }

    @Test
    @Order(2)
    internal fun `남은 카드가 있는지 확인 - 있을 때`() {
        CardDeck.getOne()
        assertThat(CardDeck.isLeft()).isTrue
    }

    // @Test
    internal fun `남은 카드가 있는지 확인 - 없을 때`() {
        repeat(48) {
            CardDeck.getOne()
        }
        assertThat(CardDeck.isLeft()).isFalse
    }
}

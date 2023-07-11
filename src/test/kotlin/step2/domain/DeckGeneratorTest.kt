package step2.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckGeneratorTest {

    @Test
    fun `CardDeck 생성에 성공한다`() {
        // when
        val cardDeck = DeckGenerator.generateCardDeck()

        // then
        assertThat(cardDeck.cards.size).isSameAs(Denomination.values().size * Suit.values().size)
    }
}

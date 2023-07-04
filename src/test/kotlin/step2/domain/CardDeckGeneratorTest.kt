package step2.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardDeckGeneratorTest {

    @Test
    fun `CardDeck 생성에 성공한다`() {
        // when
        val cardDeck = CardDeckGenerator.generateCardDeck()

        // then
        assertThat(cardDeck.cards.size).isSameAs(CardScore.values().size * CardShape.values().size)
    }
}

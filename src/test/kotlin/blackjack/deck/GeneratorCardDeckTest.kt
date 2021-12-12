package blackjack.deck

import blackjack.domain.deck.GeneratorCardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GeneratorCardDeckTest {

    @Test
    fun `카드 덱에 카드 52개가 생성되는지 확인한다`() {
        val cardDeck = GeneratorCardDeck.generatorCardDeck()
        assertThat(cardDeck.size).isEqualTo(CARD_DECK_SIZE)
    }
    companion object {
        private const val CARD_DECK_SIZE = 52
    }
}

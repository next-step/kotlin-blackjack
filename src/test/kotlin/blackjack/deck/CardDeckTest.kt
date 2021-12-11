package blackjack.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `카드 덱에 카드 52개가 생성되는지 확인한다`() {
        assertThat(CardDeck.cards.size).isEqualTo(CARD_DECK_SIZE)
    }

    companion object {
        private const val CARD_DECK_SIZE = 52
    }
}

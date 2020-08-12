package blackjack

import blackjack.model.Card
import blackjack.model.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    private val cardDeck = CardDeck()

    @Test
    fun `카드 뽑기 테스트`() {
        assertThat(cardDeck.pickCard() is Card).isEqualTo(true)
    }
}

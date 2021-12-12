package blackjack.deck

import blackjack.domain.deck.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    @Order(1)
    fun `카드덱에서 카드 한장을 받는다`() {
        assertThat(CardDeck.draw()).isNotNull
    }
}

package blackjack

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.Denomination
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    private val notShuffledDeck = CardDeck()

    @Test
    fun `카드 뽑기 테스트`() {
        assertThat(notShuffledDeck.drawCard() is Card).isEqualTo(true)
        assertThat(notShuffledDeck.drawCard()).isEqualTo(Card(Suit.CLUBS, Denomination.TWO))
    }
}

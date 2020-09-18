package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱 사이즈가 52가 맞는지 확인`() {
        assertThat(Deck().deck.size).isEqualTo(52)
    }

    @Test
    fun `덱에서 카드가 뽑히는지 확인`() {
        assertThat(Deck().getCard()).isEqualTo(Card(Denomination.ACE, Suit.CLUB))
    }
}

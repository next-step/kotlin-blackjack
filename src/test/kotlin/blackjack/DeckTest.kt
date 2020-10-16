package blackjack

import blackjack.domain.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱 사이즈가 52가 맞는지 확인`() {
        val deck = Deck()
        assertThat(deck.size).isEqualTo(52)
    }

    @Test
    fun `덱이 카드 주면 카드가 하나 없어 져야 함`(){
        val deck = Deck()
        deck.giveCard(deck.shuffled())
        assertThat(deck.shuffled().size).isEqualTo(51)
    }
}

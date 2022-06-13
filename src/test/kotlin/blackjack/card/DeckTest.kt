package blackjack.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class DeckTest {
    @Test
    fun `덱을 구성하는 카드는 13*4 = 52개다`() {
        val deck = Deck.init()
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @ParameterizedTest
    @EnumSource(Suit::class)
    fun `최초 생성된 덱은 4개 타입, 각 13개의 심볼을 갖고 있다`(suit: Suit) {
        val deck = Deck.init()
        assertThat(deck.cards.count { it.suit == suit }).isEqualTo(13)
    }
}

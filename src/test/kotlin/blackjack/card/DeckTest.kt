package blackjack.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `덱 구성 카드는 52개다`() {
        val deck = Deck.init()
        assertThat(deck.size).isEqualTo(52)
    }

    @Test
    fun `카드 수 이상으로 카드를 뽑으면 에러가 발생한다`() {
        val deck = Deck.init()
        repeat(deck.size) {
            deck.draw()
        }
        assertThrows<NoSuchElementException> { deck.draw() }
    }
}

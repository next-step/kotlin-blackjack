package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱이 비어있는지 여부를 알 수 있다`() {
        val deck = Deck.empty()
        assertThat(deck.isEmpty()).isTrue
    }

    @Test
    fun `덱의 가장 위에 있는 카드가 무엇인지 확인할 수 있다`() {
        assertThat(Deck.shuffled().peek()).isNotNull
    }
}

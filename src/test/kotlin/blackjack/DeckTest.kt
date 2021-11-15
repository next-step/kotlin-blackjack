package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱에 남아있는 카드 개수를 알 수 있다`() {
        val deck = Deck.empty()
        assertThat(deck.size).isZero
    }

    @Test
    fun `덱이 비어있는지 여부를 알 수 있다`() {
        val deck = Deck.empty()
        assertThat(deck.isEmpty()).isTrue
    }

    @Test
    fun `덱을 섞으면 서로 다른 52개의 카드가 있다`() {
        val deck = Deck.shuffled()
        assertThat(deck.size).isEqualTo(52)
    }

    @Test
    fun `덱의 가장 위에 있는 카드가 무엇인지 확인할 수 있다`() {
        assertThat(Deck.shuffled().peek()).isNotNull
    }

    @Test
    fun `덱의 가장 위에 있는 카드를 뽑을 수 있다`() {
        assertThat(Deck.shuffled().draw().size).isEqualTo(51)
    }
}

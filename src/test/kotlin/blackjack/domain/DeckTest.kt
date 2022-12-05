package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    internal fun `Deck 은 48장의 카드가 생성된다`() {
        // given
        val deck = Deck.init()

        // when, then
        assertThat(deck.size).isEqualTo(48)
    }

    @Test
    internal fun `한장의 카드를 뽑고, 카드 한장이 줄어든다`() {
        // given
        val deck = Deck.init()

        // when
        deck.draw()

        // then
        assertThat(deck.size).isEqualTo(47)
    }

    @Test
    internal fun `카드가 없는상태에서 카드를 뽑으면 예외가 발생한다`() {
        // given
        val deck = Deck(mutableSetOf())

        // when
        assertThatIllegalStateException().isThrownBy {
            deck.draw()
        }
    }
}

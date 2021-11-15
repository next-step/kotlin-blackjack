package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.EmptyStackException

internal class DeckTest {

    @Test
    fun `덱에서 카드를 52장 뽑고 카드를 뽑으면 예외를 던진다`() {
        val deck = Deck()

        repeat(52) {
            deck.drawCard()
        }

        assertThrows<EmptyStackException> { deck.drawCard() }
    }

    @Test
    fun `덱에서 카드를 뽑을 수 있다`() {
        val deck = Deck()

        val actual = deck.drawCard()

        assertThat(actual).isNotNull
    }
}

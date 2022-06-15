package game.blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("카드 덱")
internal class DeckTest {

    @Test
    fun `덱은 52장의 중복없는 카드를 가진다`() {
        val deck = Deck()
        val drawCard = mutableSetOf<Card>()

        repeat(52) {
            drawCard.add(deck.draw())
        }

        Assertions.assertThat(drawCard).hasSize(52)
    }

    @Test
    fun `덱의 모든 카드를 draw 하면 예외가 발생한다`() {
        val deck = Deck()
        val drawCard = mutableSetOf<Card>()

        assertThrows<RuntimeException> {
            repeat(53) {
                drawCard.add(deck.draw())
            }
        }
    }
}

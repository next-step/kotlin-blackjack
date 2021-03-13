package blackjack

import blackjack.CardTest.Card
import blackjack.CardTest.Symbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    internal fun `플레이어는 받은 카드 목록이 있다`() {
        val player = Player()
            .apply {
                draw(Card("2", Symbol.HEARTS))
                draw(Card("8", Symbol.SPADES))
            }
        assertThat(player.cards).containsExactly(
            Card("2", Symbol.HEARTS),
            Card("8", Symbol.SPADES)
        )
    }

    class Player {
        var cards: List<Card> = emptyList()
            private set

        fun draw(card: Card) {
            cards = cards + card
        }
    }
}

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

    @Test
    internal fun `카드목록의 합을 계산한다`() {
        val player = Player()
            .apply {
                draw(Card("2", Symbol.HEARTS))
                draw(Card("8", Symbol.SPADES))
            }
        assertThat(player.score()).isEqualTo(10)
    }

    class Player {
        var cards: List<Card> = emptyList()
            private set
        fun score(): Int = cards.map { it.number[0] }.sum()

        fun draw(card: Card) {
            cards = cards + card
        }
    }
}

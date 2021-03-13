package blackjack

import blackjack.CardTest.Card
import blackjack.CardTest.Symbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.abs

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

    @Test
    fun `Ace 는 21 에 가까운 수로 선택한다`() {
        assertThat(
            Player()
                .apply {
                    draw(Card("A", Symbol.HEARTS))
                    draw(Card("K", Symbol.SPADES))
                }.score()
        ).isEqualTo(21)
    }

    @Test
    fun `Ace 가 포함된 합이 21을 초과하면 1로 계산한다`() {
        assertThat(
            Player()
                .apply {
                    draw(Card("A", Symbol.HEARTS))
                    draw(Card("10", Symbol.DIAMONDS))
                    draw(Card("2", Symbol.CLUBS))
                }.score()
        ).isEqualTo(13)
    }

    class Player {
        var cards: List<Card> = emptyList()
            private set

        fun score(): Int {
            val result = cards.fold(listOf(0)) { acc, card ->
                acc.flatMap { score -> card.number.map { it + score } }
            }
            return result
                .filter { 21 <= it }
                .closeTo(21)
        }

        fun draw(card: Card) {
            cards = cards + card
        }

        private fun List<Int>.closeTo(number: Int): Int {
            return map { it to abs(it - number) }.minBy { it.second }?.first ?: 0
        }
    }
}

package blackjack

import blackjack.CardTest.Card
import blackjack.CardTest.Symbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.abs

class PlayerTest {
    @Test
    internal fun `플레이어는 받은 카드 목록이 있다`() {
        val player = Player.Person("pobi")
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
        val player = Player.Person("pobi")
            .apply {
                draw(Card("2", Symbol.HEARTS))
                draw(Card("8", Symbol.SPADES))
            }
        assertThat(player.score()).isEqualTo(10)
    }

    @Test
    fun `Ace 는 21 에 가까운 수로 선택한다`() {
        assertThat(
            Player.Person("pobi")
                .apply {
                    draw(Card("A", Symbol.HEARTS))
                    draw(Card("K", Symbol.SPADES))
                }.score()
        ).isEqualTo(21)
    }

    @Test
    fun `Ace 가 포함된 합이 21을 초과하면 1로 계산한다`() {
        assertThat(
            Player.Person("pobi")
                .apply {
                    draw(Card("A", Symbol.HEARTS))
                    draw(Card("10", Symbol.DIAMONDS))
                    draw(Card("2", Symbol.CLUBS))
                }.score()
        ).isEqualTo(13)
    }

    @Test
    fun `21을 초과할 수 있다`() {
        assertThat(
            Player.Person("pobi")
                .apply {
                    draw(Card("8", Symbol.HEARTS))
                    draw(Card("8", Symbol.DIAMONDS))
                    draw(Card("8", Symbol.CLUBS))
                }.score()
        ).isEqualTo(24)
    }

    interface Player {
        val name: String
        val cards: List<Card>
        fun score(): Int
        fun draw(card: Card) = draw({ card })
        fun draw(
            nextCard: () -> Card,
            isDraw: (name: String) -> Boolean = { true },
            result: (Player) -> Unit = {}
        )

        class Person(override val name: String) : Player {
            private var _cards: List<Card> = emptyList()
            override val cards: List<Card>
                get() = _cards

            override fun score(): Int {
                return _cards.fold(listOf(0)) { acc, card ->
                    acc.flatMap { score -> card.number.map { it + score } }
                }.closeTo(21)
            }

            override fun draw(card: Card) {
                _cards = _cards + card
            }

            override fun draw(nextCard: () -> Card, isDraw: (name: String) -> Boolean, result: (Player) -> Unit) {
                if (isDraw(name)) {
                    draw(nextCard())
                    result(this)
                }
            }

            private fun List<Int>.closeTo(number: Int): Int {
                val sorted = map { it to abs(it - number) }
                    .sortedBy { it.second }
                    .map { it.first }
                val result = sorted.firstOrNull { it <= 21 }
                return result ?: sorted.firstOrNull() ?: 0
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Person

                if (name != other.name) return false
                if (_cards != other._cards) return false

                return true
            }

            override fun hashCode(): Int {
                var result = name.hashCode()
                result = 31 * result + _cards.hashCode()
                return result
            }
        }
    }
}

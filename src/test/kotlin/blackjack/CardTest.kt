package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    // blackjack {
    //     ace(1, 10)
    //     normal(2..10)
    //     jack(10)
    //     queen(10)
    //     king(10)
    // }

    @Test
    internal fun 에이스() {
        val blackjack = blackjack {
            ace(1, 10)
        }.build()
        assertThat(blackjack.deck).contains(
            Card("A", Shape.HEART, listOf(1, 10)),
            Card("A", Shape.CLOVER, listOf(1, 10)),
            Card("A", Shape.SPADE, listOf(1, 10)),
            Card("A", Shape.DIAMOND, listOf(1, 10))
        )
    }

    private fun blackjack(initializer: BlackjackBuilder.() -> Unit): BlackjackBuilder {
        return BlackjackBuilder().apply(initializer)
    }

    class BlackjackBuilder {
        var deck: List<Card> = emptyList()

        fun ace(vararg numbers: Int) {
            val scores = numbers.asList()
            deck = deck + Shape.values().map { Card("A", it, scores) }
        }

        fun build(): Blackjack = Blackjack(deck)
    }

    class Blackjack(val deck: List<Card>)

    data class Card(private val name: String, private val shape: Shape, private val number: List<Int>)

    enum class Shape(private val shapeName: String) {
        HEART("하트"), CLOVER("클로버"), SPADE("스페이드"), DIAMOND("다이아몬드")
    }
}

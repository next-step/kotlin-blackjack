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
            Card("A", Symbol.HEARTS, listOf(1, 10)),
            Card("A", Symbol.CLUBS, listOf(1, 10)),
            Card("A", Symbol.SPADES, listOf(1, 10)),
            Card("A", Symbol.DIAMONDS, listOf(1, 10))
        )
    }

    @Test
    internal fun 노멀() {
        val blackjack = blackjack {
            normal(2..10)
        }.build()
        assertThat(blackjack.deck).contains(
            Card("2", Symbol.HEARTS, listOf(2)),
            Card("3", Symbol.CLUBS, listOf(3)),
            Card("9", Symbol.SPADES, listOf(9)),
            Card("10", Symbol.DIAMONDS, listOf(10))
        )
    }

    private fun blackjack(initializer: BlackjackBuilder.() -> Unit): BlackjackBuilder {
        return BlackjackBuilder().apply(initializer)
    }

    class BlackjackBuilder {
        var deck: List<Card> = emptyList()

        fun ace(vararg numbers: Int) {
            val scores = numbers.asList()
            deck = deck + Symbol.values().map { Card("A", it, scores) }
        }

        fun normal(numbers: IntRange) {
            val symbolCard = { number: Int ->
                Symbol.values().map { Card(number, it, number) }
            }
            deck = deck + numbers.map { symbolCard(it) }.flatten()
        }

        fun build(): Blackjack = Blackjack(deck)
    }

    class Blackjack(val deck: List<Card>)

    data class Card(private val name: String, private val symbol: Symbol, private val number: List<Int>) {
        constructor(name: Int, symbol: Symbol, number: Int) : this(name.toString(), symbol, listOf(number))
    }

    enum class Symbol(private val symbolName: String) {
        HEARTS("하트"),
        CLUBS("클로버"),
        SPADES("스페이드"),
        DIAMONDS("다이아몬드")
    }
}

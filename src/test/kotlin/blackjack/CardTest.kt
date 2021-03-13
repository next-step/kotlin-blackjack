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
            Card("2", Symbol.HEARTS, 2),
            Card("3", Symbol.CLUBS, 3),
            Card("9", Symbol.SPADES, 9),
            Card("10", Symbol.DIAMONDS, 10)
        )
    }

    @Test
    internal fun `잭, 퀸, 킹`() {
        val blackjack = blackjack {
            jack(10)
            queen(10)
            king(10)
        }.build()
        assertThat(blackjack.deck).contains(
            Card("J", Symbol.HEARTS, 10),
            Card("Q", Symbol.CLUBS, 10),
            Card("K", Symbol.SPADES, 10),
            Card("J", Symbol.DIAMONDS, 10)
        )
    }

    private fun blackjack(initializer: BlackjackBuilder.() -> Unit): BlackjackBuilder {
        return BlackjackBuilder().apply(initializer)
    }

    class BlackjackBuilder {
        var deck: List<Card> = emptyList()

        fun normal(numbers: IntRange) {
            deck = deck + numbers.map { toSymbolCard(it.toString(), it) }.flatten()
        }

        fun ace(vararg numbers: Int) {
            deck = deck + toSymbolCard("A", *numbers)
        }

        fun jack(number: Int) {
            deck = deck + toSymbolCard("J", number)
        }

        fun queen(number: Int) {
            deck = deck + toSymbolCard("Q", number)
        }

        fun king(number: Int) {
            deck = deck + toSymbolCard("K", number)
        }

        private fun toSymbolCard(name: String, vararg numbers: Int): List<Card> {
            return Symbol.values().map { Card(name, it, numbers.toList()) }
        }

        fun build(): Blackjack = Blackjack(deck)
    }

    class Blackjack(val deck: List<Card>)

    data class Card(private val name: String, private val symbol: Symbol, private val number: List<Int>) {
        constructor(name: String, symbol: Symbol, number: Int) : this(name, symbol, listOf(number))
    }

    enum class Symbol(private val symbolName: String) {
        HEARTS("하트"),
        CLUBS("클로버"),
        SPADES("스페이드"),
        DIAMONDS("다이아몬드")
    }
}

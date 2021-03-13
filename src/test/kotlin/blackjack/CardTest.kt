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
            ace()
        }.build()
        assertThat(blackjack.deck).contains(
            Card("A", Symbol.HEARTS),
            Card("A", Symbol.CLUBS),
            Card("A", Symbol.SPADES),
            Card("A", Symbol.DIAMONDS)
        )
    }

    @Test
    internal fun 노멀() {
        val blackjack = blackjack {
            normal(2..10)
        }.build()
        assertThat(blackjack.deck).contains(
            Card("2", Symbol.HEARTS),
            Card("3", Symbol.CLUBS),
            Card("9", Symbol.SPADES),
            Card("10", Symbol.DIAMONDS)
        )
    }

    @Test
    internal fun `잭, 퀸, 킹`() {
        val blackjack = blackjack {
            jack()
            queen()
            king()
        }.build()
        assertThat(blackjack.deck).contains(
            Card("J", Symbol.HEARTS),
            Card("Q", Symbol.CLUBS),
            Card("K", Symbol.SPADES),
            Card("J", Symbol.DIAMONDS)
        )
    }

    private fun blackjack(initializer: BlackjackBuilder.() -> Unit): BlackjackBuilder {
        return BlackjackBuilder().apply(initializer)
    }

    class BlackjackBuilder {
        var deck: List<Card> = emptyList()

        fun normal(numbers: IntRange) {
            deck = deck + numbers.map { toSymbolCard(it.toString()) }.flatten()
        }

        fun ace() {
            deck = deck + toSymbolCard("A")
        }

        fun jack() {
            deck = deck + toSymbolCard("J")
        }

        fun queen() {
            deck = deck + toSymbolCard("Q")
        }

        fun king() {
            deck = deck + toSymbolCard("K")
        }

        private fun toSymbolCard(name: String): List<Card> {
            return Symbol.values().map { Card(name, it) }
        }

        fun build(): Blackjack = Blackjack(deck)
    }

    class Blackjack(val deck: List<Card>)

    data class Card(private val name: String, private val symbol: Symbol) {
        val number: List<Int> = when (name) {
            "A" -> listOf(1, 11)
            "J", "Q", "K" -> listOf(10)
            else -> listOf(name.toInt())
        }
    }

    enum class Symbol(private val symbolName: String) {
        HEARTS("하트"),
        CLUBS("클로버"),
        SPADES("스페이드"),
        DIAMONDS("다이아몬드")
    }
}

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
        }
        assertThat(blackjack.deck).contains(
            Card("A", "하트", listOf(1, 10)),
            Card("A", "클로버", listOf(1, 10)),
            Card("A", "스페이드", listOf(1, 10)),
            Card("A", "다이아몬드", listOf(1, 10))
        )
    }

    private fun blackjack(initializer: BlackjackBuilder.() -> Unit): BlackjackBuilder {
        return BlackjackBuilder().apply(initializer)
    }

    class BlackjackBuilder {

        var deck: List<Card> = emptyList()

        fun ace(vararg numbers: Int) {
            deck = deck + listOf(
                Card("A", "하트", numbers.asList()),
                Card("A", "클로버", numbers.asList()),
                Card("A", "스페이드", numbers.asList()),
                Card("A", "다이아몬드", numbers.asList())
            )
        }
    }

    data class Card(private val name: String, private val shape: String, private val number: List<Int>)
}

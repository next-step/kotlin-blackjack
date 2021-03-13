package blackjack

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
        assertThat(blackjack.cards).contains(Card("A", "하트"), Card("A", "클로버"), Card("A", "스페이드"), Card("A", "다이아몬드"))
    }
}

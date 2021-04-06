package blackjack.domain

import blackjack.domain.CardSuite
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardSuiteTests {
    @Test
    fun `다이아몬드, 하트, 스페이드, 클로버가 있다`() {
        assertThat(CardSuite.values())
            .containsExactlyInAnyOrder(
                CardSuite.HEART,
                CardSuite.DIAMOND,
                CardSuite.SPADE,
                CardSuite.CLOVER
            )
    }
}

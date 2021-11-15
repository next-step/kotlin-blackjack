package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreHelperTest {

    @Test
    fun `카드들의 기본 점수를 합산할 수 있다`() {
        val cards = listOf(
            Card(Denomination.TWO, Suit.HEART),
            Card(Denomination.TWO, Suit.CLOVER),
            Card(Denomination.TWO, Suit.HEART),
        )
        assertThat(ScoreHelper.sum(cards)).isEqualTo(6)
    }
}

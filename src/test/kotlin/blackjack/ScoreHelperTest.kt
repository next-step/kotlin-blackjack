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

    @Test
    fun `Ace를 제외한 나머지 점수들의 합이 10이하라면 Ace는 11점으로 계산한다`() {
        val cards = listOf(
            Card(Denomination.ACE, Suit.HEART),
            Card(Denomination.TEN, Suit.HEART)
        )
        assertThat(ScoreHelper.sum(cards)).isEqualTo(21)
    }

    @Test
    fun `Ace를 제외한 나머지 점수들의 합이 10보다 크다면 Ace는 1점으로 계산한다`() {
        val cards = listOf(
            Card(Denomination.ACE, Suit.HEART),
            Card(Denomination.TEN, Suit.HEART),
            Card(Denomination.TEN, Suit.CLOVER),
        )
        assertThat(ScoreHelper.sum(cards)).isEqualTo(21)
    }
}

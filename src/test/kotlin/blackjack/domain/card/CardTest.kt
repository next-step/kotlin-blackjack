package blackjack.domain.card

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `card로 점수 계산이 가능하다 TWO(2) + 1 = 3`() {
        val card = Card(Suit.CLUB, Denomination.TWO)
        Assertions.assertThat(card.calculateScore(Score.of(1))).isEqualTo(Score.of(3))
    }

    @Test
    fun `card로 점수 계산이 가능하다 ACE(11) + 10 = 21`() {
        val card = Card(Suit.CLUB, Denomination.ACE)
        Assertions.assertThat(card.calculateScore(Score.of(10))).isEqualTo(Score.of(21))
    }

    @Test
    fun `card ACE 일 때 합이 21점을 초과 한다면 ACE는 1점으로 취급한다 ACE(1) + 11 = 12`() {
        val card = Card(Suit.CLUB, Denomination.ACE)
        Assertions.assertThat(card.calculateScore(Score.of(11))).isEqualTo(Score.of(12))
    }
}

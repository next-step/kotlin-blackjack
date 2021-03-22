package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardsTest {

    private val aceCard = Card(Suit.CLUB, Denomination.ACE)
    private val tenCard = Card(Suit.CLUB, Denomination.TEN)
    private val threeCard = Card(Suit.CLUB, Denomination.THREE)

    @Test
    fun `cards 는 내부의 점수를 계산할 수 있다 10 + 3 = 13`() {
        val cards = Cards(tenCard, threeCard)
        assertThat(cards.score).isEqualTo(Score.of(13))
    }

    @Test
    fun `cards 는 내부의 점수를 계산할 수 있다 ACE가 11점으로 계산 되는 경우 11 + 10 = 21`() {
        val cards = Cards(aceCard, tenCard)
        assertThat(cards.score).isEqualTo(Score.of(21))
    }

    @Test
    fun `cards 는 내부의 점수를 계산할 수 있다 ACE가 1점으로 계산 되는 경우 1 + 10 + 3 = 14`() {
        val cards = Cards(aceCard, tenCard, threeCard)
        assertThat(cards.score).isEqualTo(Score.of(14))
    }
}

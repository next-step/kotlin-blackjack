package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `점수를 계산한다`() {
        assertThat(
            Card(Suit.CLUB, Denomination.NINE).calculateScore(
                listOf(
                    Card(Suit.CLUB, Denomination.NINE),
                    Card(Suit.CLUB, Denomination.NINE)
                )
            )
        ).isEqualTo(Score(27))
    }
}

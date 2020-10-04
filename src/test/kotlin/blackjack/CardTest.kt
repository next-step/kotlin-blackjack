package blackjack

import domain.Card
import domain.Denomination
import domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드에서 점수 출력`() {
        val sampleCard = Card(Pair(Suit.CLUB, Denomination.FIVE))
        assertThat(sampleCard.score()).isEqualTo(5)
    }
}

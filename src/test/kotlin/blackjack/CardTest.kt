package blackjack


import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드에서 점수 출력`() {
        val sampleCard = Card(
            Pair(
                Suit.CLUB,
                Denomination.FIVE
            )
        )
        assertThat(sampleCard.score()).isEqualTo(5)
    }

    @Test
    fun `카드 이름 출력`() {
        val sampleCard = Card(
            Pair(
                Suit.CLUB,
                Denomination.FIVE
            )
        )
        assertThat(sampleCard.toString()).isEqualTo("5클로버")
    }
}

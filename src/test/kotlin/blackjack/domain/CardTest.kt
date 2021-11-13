package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `카드는 모양과 끝수로 구성된다`() {
        val card = Card(Suit.CLUB, Denomination.TWO)

        assertAll(
            { assertThat(card.denomination).isEqualTo(Denomination.TWO) },
            { assertThat(card.suit).isEqualTo(Suit.CLUB) }
        )
    }
}

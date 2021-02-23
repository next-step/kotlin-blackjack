package blackjack.domain.deck

import blackjack.domain.deck.Card
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `에이스_카드_여부_확인`() {
        val ace = Card(Denomination.ACE, Suit.CLOVER)

        assertThat(ace.isAce()).isTrue()
    }
}

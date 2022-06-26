package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun isAce() {
        assertThat(Card(CardNumber.ACE, CardSuit.CLOVER).isAce()).isTrue
    }
}

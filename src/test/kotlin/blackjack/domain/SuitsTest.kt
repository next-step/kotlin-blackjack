package blackjack.domain

import blackjack.domain.deck.Suits
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SuitsTest {
    @Test
    fun `clubs, diamonds, hearts, spades 4가지 종류의 모양을 가진다`() {
        assertThat(Suits.values().size).isEqualTo(4)
        assertThat(Suits.CLUBS).isEqualTo(Suits.CLUBS)
        assertThat(Suits.DIAMONDS).isEqualTo(Suits.DIAMONDS)
        assertThat(Suits.HEARTS).isEqualTo(Suits.HEARTS)
        assertThat(Suits.SPADES).isEqualTo(Suits.SPADES)
    }
}

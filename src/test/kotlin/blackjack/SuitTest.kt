package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SuitTest {

    @Test
    fun `get suit symbol`() {
        val sample = Suit.CLUB
        val sample2 = Suit.DIAMOND
        assertThat(sample.shapeName).isEqualTo("클로버")
        assertThat(sample2.shapeName).isEqualTo("다이아몬드")
    }

}

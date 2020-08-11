package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardScoreTest {

    @Test
    fun `card score`() {
        assertThat(CardScore.ACE.score).isEqualTo(1)
        assertThat(CardScore.TWO.score).isEqualTo(2)
        assertThat(CardScore.THREE.score).isEqualTo(3)
        assertThat(CardScore.FOUR.score).isEqualTo(4)
        assertThat(CardScore.FIVE.score).isEqualTo(5)
        assertThat(CardScore.SIX.score).isEqualTo(6)
        assertThat(CardScore.SEVEN.score).isEqualTo(7)
        assertThat(CardScore.EIGHT.score).isEqualTo(8)
        assertThat(CardScore.NINE.score).isEqualTo(9)
        assertThat(CardScore.TEN.score).isEqualTo(10)
        assertThat(CardScore.JACK.score).isEqualTo(10)
        assertThat(CardScore.QUEEN.score).isEqualTo(10)
        assertThat(CardScore.KING.score).isEqualTo(10)
    }

    @Test
    fun `initial of card`() {
        // when
        val initialOfAce = CardScore.initialOfCard(CardScore.ACE)
        val initialOfTen = CardScore.initialOfCard(CardScore.TEN)

        // then
        assertThat(initialOfAce).isEqualTo("A")
        assertThat(initialOfTen).isEqualTo("10")
    }
}

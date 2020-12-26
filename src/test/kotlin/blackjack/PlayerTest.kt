package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PlayerTest {
    private val sampleCard1 = Card(Pair(Denomination.FIVE, Suit.SPADE))
    private val sampleCard2 = Card(Pair(Denomination.QUEEN, Suit.CLUB))
    private val samplePlayer = Player("Jack")

    init {
        samplePlayer.draw(sampleCard1)
        samplePlayer.draw(sampleCard2)
    }

    @Test
    fun `get total player's score`() {

        assertThat(samplePlayer.playerScore()).isEqualTo(15)
    }

    @Test
    fun `check state of Card`() {

        assertThat(samplePlayer.stateCards()).isEqualTo("[5스페이드, Q클로버]")
    }

    @Test
    fun `test chooseDraw function`() {
        val sampleCard3 = Card(Pair(Denomination.KING, Suit.SPADE))
        val sampleCard4 = Card(Pair(Denomination.KING, Suit.HEART))
        // the player's score is 25
        samplePlayer.draw(sampleCard3)
        // It not improve score if player's score more than 21
        assertThrows<IllegalArgumentException> {
            samplePlayer.chooseDraw(sampleCard4)
        }
    }
}
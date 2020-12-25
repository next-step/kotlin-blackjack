package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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

        assertThat(samplePlayer.chooseDraw(sampleCard3)).isEqualTo(null)
    }
}
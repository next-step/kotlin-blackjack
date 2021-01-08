package blackjack

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    private val sampleCard1 = Card(Pair(Denomination.FIVE, Suit.SPADE))
    private val sampleCard2 = Card(Pair(Denomination.QUEEN, Suit.CLUB))
    private val samplePlayer = Player("Jack")

    init {
        samplePlayer.hit(sampleCard1)
        samplePlayer.hit(sampleCard2)
    }

    @Test
    fun `get total player's score`() {

        assertThat(samplePlayer.playerScore()).isEqualTo(15)
    }

    @Test
    fun `get total amount of cards`() {
        assertThat(samplePlayer.amountOfCards()).isEqualTo(2)
    }

    @Test
    fun `check state of Card`() {

        assertThat(samplePlayer.stateCards()).isEqualTo("[5스페이드, Q클로버]")
    }

    @Test
    fun `test chooseDraw function`() {
        val sampleCard3 = Card(Pair(Denomination.KING, Suit.SPADE))
        val sampleCard4 = Card(Pair(Denomination.KING, Suit.HEART))
        samplePlayer.chooseDraw(REPLY_STAND, sampleCard3)
        assertThat(samplePlayer.amountOfCards()).isEqualTo(2)
        samplePlayer.chooseDraw(REPLY_HIT, sampleCard3)
        assertThat(samplePlayer.amountOfCards()).isEqualTo(3)
        // the player's score is 25
        // samplePlayer.draw(sampleCard3)
        // It not improve score if player's score more than 21
        samplePlayer.chooseDraw(REPLY_HIT, sampleCard4)
        assertThat(samplePlayer.amountOfCards()).isEqualTo(3)
    }

    companion object {
        val REPLY_HIT = "y"
        val REPLY_STAND = "n"
    }
}

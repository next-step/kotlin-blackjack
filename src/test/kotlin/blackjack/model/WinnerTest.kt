package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinnerTest {

    @Test
    fun `점수 계산하기`() {
        val player = Player("moshi").apply {
            requestDeck(Deck(Card.Type.TWO, Card.Shape.HEART))
            requestDeck(Deck(Card.Type.EIGHT, Card.Shape.SPADE))
            requestDeck(Deck(Card.Type.ACE, Card.Shape.CLOVER))
        }

        assertThat(Winner.calculateRank(player.myReceivedDeck)).isEqualTo(21)
    }
}

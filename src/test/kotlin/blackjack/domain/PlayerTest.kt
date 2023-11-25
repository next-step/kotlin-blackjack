package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `주어진 카드로부터 Score를 계산한다`() {
        // given
        val player = Player("test")
        val newCard1 = Card(denomination = Denomination.TEN, suit = Suit.SPADES)
        val newCard2 = Card(denomination = Denomination.TWO, suit = Suit.HEARTS)
        player.getCard(newCard1)
        player.getCard(newCard2)

        // when
        val score = player.getScore()

        // then
        assertEquals(score, 12)
    }

    @Test
    fun `블랙잭 점수(21점)이 넘었을 경우 Bust되어 플레이어는 stand로 의사결정을 내린다`() {
        // given
        val player = Player("test")
        val newCard1 = Card(denomination = Denomination.TEN, suit = Suit.SPADES)
        val newCard2 = Card(denomination = Denomination.JACK, suit = Suit.HEARTS)
        val newCard3 = Card(denomination = Denomination.QUEEN, suit = Suit.CLUBS)

        // when
        player.getCard(newCard1)
        player.getCard(newCard2)
        player.getCard(newCard3)

        // then
        assertEquals(player.getScore(), 30)
        assertEquals(player.decision, PlayerDecision.STAND)
    }
}

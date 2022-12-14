package blackjack.service

import blackjack.model.Card
import blackjack.model.Denomination
import blackjack.model.Player
import blackjack.model.Suit
import blackjack.service.FinalScoreCalculator.finalScoreOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FInalScoreCalculatorTest {
    @Test
    internal fun `총 점수를 계산한다`() {
        val player = Player("jason")
        player.addCard(Card(Suit.SPADE, Denomination.TWO))
        player.addCard(Card(Suit.SPADE, Denomination.FIVE))
        player.addCard(Card(Suit.SPADE, Denomination.QUEEN))
        assertThat(finalScoreOf(player)).isEqualTo(17)
    }

    @Test
    internal fun `Ace가 포함된 총 점수를 계산한다`() {
        val player = Player("jason")
        player.addCard(Card(Suit.SPADE, Denomination.ACE))
        player.addCard(Card(Suit.HEART, Denomination.ACE))
        player.addCard(Card(Suit.CLOVER, Denomination.ACE))
        player.addCard(Card(Suit.DIAMOND, Denomination.ACE))
        assertThat(finalScoreOf(player)).isEqualTo(14)
    }
}

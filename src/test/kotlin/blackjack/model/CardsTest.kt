package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    internal fun `총 점수를 계산한다`() {
        val cards = Cards.of(
            Card(Suit.SPADE, Denomination.TWO),
            Card(Suit.SPADE, Denomination.FIVE),
            Card(Suit.SPADE, Denomination.QUEEN)
        )
        Assertions.assertThat(cards.getFinalScore()).isEqualTo(17)
    }

    @Test
    internal fun `Ace가 포함된 총 점수를 계산한다`() {
        val player = Player("jason")
        player.addCard(Card(Suit.SPADE, Denomination.ACE))
        player.addCard(Card(Suit.HEART, Denomination.ACE))
        player.addCard(Card(Suit.CLOVER, Denomination.ACE))
        player.addCard(Card(Suit.DIAMOND, Denomination.ACE))
        Assertions.assertThat(player.getFinalScore()).isEqualTo(14)
    }
}

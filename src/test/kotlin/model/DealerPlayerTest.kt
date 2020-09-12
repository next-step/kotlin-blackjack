package model

import game.BlackJackWinner
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DealerPlayerTest {
    @Test
    @DisplayName("dealer 21점 이상이면 lose 를 반환한다")
    fun `over21ScoreLose`() {
        val dealer = DealerPlayer()
        val player = Player("hello")
        val cards = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.QUEEN), Card(Suit.CLUBS, Denomination.QUEEN), Card(Suit.CLUBS, Denomination.QUEEN))
        val cardDealer = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.QUEEN), Card(Suit.CLUBS, Denomination.QUEEN), Card(Suit.CLUBS, Denomination.QUEEN))
        receiveCard(dealer, cardDealer)
        receiveCard(player, cards)
        Assertions.assertThat(dealer.compareResult(player)).isEqualTo(BlackJackWinner.LOSE)
    }

    private fun receiveCard(player: AbstractPlayer, list: List<Card>) {
        for (card in list) {
            player.receive(card)
        }
    }
}

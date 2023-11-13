package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Number
import blackjack.dto.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {

    @Test
    fun `초기 카드는 두 장씩 나눠준다`() {
        val participants = Participants(
            Players(
                listOf(
                    Player("a"),
                    Player("b")
                )
            ),
            Dealer()
        )
        participants.initialCardDealing()
        assertThat(participants.players.players[0].cards).hasSize(2)
        assertThat(participants.players.players[1].cards).hasSize(2)
        assertThat(participants.dealer.cards).hasSize(2)
    }

    @Test
    fun `결과를 구한다`() {
        val players = Players(listOf(Player("a"), Player("b")))
        val dealer = Dealer()
        val participants = Participants(players, dealer)

        players.players[0].addCards(listOf(Card(Suit.SPADE, Number.ACE), Card(Suit.DIAMOND, Number.KING)))
        players.players[1].addCards(listOf(Card(Suit.SPADE, Number.TWO), Card(Suit.DIAMOND, Number.QUEEN)))
        dealer.addCards(listOf(Card(Suit.SPADE, Number.THREE), Card(Suit.DIAMOND, Number.JACK)))
        participants.makeResult()

        assertThat(players.players[0].result?.winning).isEqualTo(1)
        assertThat(players.players[0].result?.losing).isEqualTo(0)
        assertThat(players.players[1].result?.winning).isEqualTo(0)
        assertThat(players.players[1].result?.losing).isEqualTo(1)
        assertThat(dealer.result?.winning).isEqualTo(1)
        assertThat(dealer.result?.losing).isEqualTo(1)
    }
}

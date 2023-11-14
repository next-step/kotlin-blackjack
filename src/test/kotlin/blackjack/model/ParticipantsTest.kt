package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Number
import blackjack.dto.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

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
        assertAll(
            { assertThat(participants.players.values[0].cards).hasSize(2) },
            { assertThat(participants.players.values[1].cards).hasSize(2) },
            { assertThat(participants.dealer.cards).hasSize(2) }
        )
    }

    @Test
    fun `결과를 구한다`() {
        // given
        val players = Players(listOf(Player("a"), Player("b")))
        val dealer = Dealer()
        val participants = Participants(players, dealer)

        players.values[0].addCards(listOf(Card(Suit.SPADE, Number.ACE), Card(Suit.DIAMOND, Number.KING)))
        players.values[1].addCards(listOf(Card(Suit.SPADE, Number.TWO), Card(Suit.DIAMOND, Number.QUEEN)))
        dealer.addCards(listOf(Card(Suit.SPADE, Number.THREE), Card(Suit.DIAMOND, Number.JACK)))

        // when
        participants.makeResult()

        // then
        assertThat(players.values[0].blackJackResult?.winning).isEqualTo(1)
        assertThat(players.values[0].blackJackResult?.losing).isEqualTo(0)
        assertThat(players.values[1].blackJackResult?.winning).isEqualTo(0)
        assertThat(players.values[1].blackJackResult?.losing).isEqualTo(1)
        assertThat(dealer.blackJackResult?.winning).isEqualTo(1)
        assertThat(dealer.blackJackResult?.losing).isEqualTo(1)
    }
}

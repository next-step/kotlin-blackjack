package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Money
import blackjack.dto.Number
import blackjack.dto.PlayerStatus
import blackjack.dto.Suit
import blackjack.model.Dealer.Companion.DEALER_NAME
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ParticipantsTest {

    @Test
    fun `초기 카드는 두 장씩 나눠준다`() {
        val participants = Participants(
            Players(
                listOf(
                    Player("a", Money.ZERO),
                    Player("b", Money.ZERO)
                )
            ),
            Dealer(DEALER_NAME, Money.ZERO)
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
        val players = Players(listOf(Player("a", Money.ZERO), Player("b", Money.ZERO)))
        val dealer = Dealer(DEALER_NAME, Money.ZERO)
        val participants = Participants(players, dealer)

        players.values[0].addCards(listOf(Card(Suit.SPADE, Number.ACE), Card(Suit.DIAMOND, Number.KING)))
        players.values[1].addCards(listOf(Card(Suit.SPADE, Number.TWO), Card(Suit.DIAMOND, Number.QUEEN)))
        dealer.addCards(listOf(Card(Suit.SPADE, Number.THREE), Card(Suit.DIAMOND, Number.JACK)))

        // when
        players.values.forEach { it.stay() }
        dealer.stay()
        participants.makeResult()

        // then
        assertAll(
            { assertThat(players.values[0].gameResult?.point).isEqualTo(21) },
            { assertThat(players.values[0].gameResult?.playerResultStatus).isEqualTo(PlayerResultStatus.BLACKJACK) },
            { assertThat(players.values[1].gameResult?.point).isEqualTo(12) },
            { assertThat(players.values[1].gameResult?.playerResultStatus).isEqualTo(PlayerResultStatus.LOSE) },
            { assertThat(dealer.gameResult?.point).isEqualTo(13) },
        )
    }

    @Test
    fun `게임을 진행한다`() {
        // given
        val players = Players(listOf(Player("a", Money.ZERO), Player("b", Money.ZERO)))
        val dealer = Dealer(DEALER_NAME, Money.ZERO)
        val participants = Participants(players, dealer)

        // when
        participants.initialCardDealing()
        participants.processGame(
            moreCardComment = {},
            hitOrStand = { false },
            showCard = {}
        )

        // then
        assertAll(
            { assertThat(players.values[0].status).isEqualTo(PlayerStatus.STAY) },
            { assertThat(players.values[0].cards).hasSize(2) },
            { assertThat(players.values[1].status).isEqualTo(PlayerStatus.STAY) },
            { assertThat(players.values[1].cards).hasSize(2) },
            { assertThat(dealer.status).isNotEqualTo(PlayerStatus.HIT) },
            { assertThat(dealer.cards.size).isIn(2, 3) },
        )
    }
}

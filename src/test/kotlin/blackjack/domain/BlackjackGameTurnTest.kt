package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackjackGameTurnTest {
    @Test
    fun `BlackjackGameTurn은 현재 블랙잭 게임의 턴을 진행할 참가자가 누구인지를 알려 준다`() {
        val participants = Participants(
            dealer = Dealer("딜러", PlayingCard(Suit.SPADES, CardNumber.NINE)),
            players = listOf(
                Player(
                    "강아지",
                    10_000,
                    PlayingCard(Suit.SPADES, CardNumber.ACE),
                    PlayingCard(Suit.SPADES, CardNumber.TEN)
                ),
                Player(
                    "고양이",
                    10_000,
                    PlayingCard(Suit.SPADES, CardNumber.TWO),
                    PlayingCard(Suit.SPADES, CardNumber.THREE)
                )
            )
        )

        val blackjackGameTurn = BlackjackGameTurn.from(participants)
        assertThat(blackjackGameTurn.participant).isEqualTo(participants.players[1])
    }

    @Test
    fun `모든 플레이어가 게임을 진행할 수 없는 상태면, 딜러에게 턴이 넘어간다`() {
        val participants = Participants(
            dealer = Dealer("딜러", PlayingCard(Suit.SPADES, CardNumber.NINE)),
            players = listOf(
                Player(
                    "강아지",
                    10_000,
                    PlayingCard(Suit.SPADES, CardNumber.ACE),
                    PlayingCard(Suit.SPADES, CardNumber.TEN)
                ),
                Player(
                    "고양이",
                    10_000,
                    PlayingCard(Suit.DIAMONDS, CardNumber.TEN),
                    PlayingCard(Suit.CLUBS, CardNumber.JACK),
                    PlayingCard(Suit.HEARTS, CardNumber.KING),
                )
            )
        )

        val blackjackGameTurn = BlackjackGameTurn.from(participants)
        assertThat(blackjackGameTurn.participant).isEqualTo(participants.dealer)
    }

    @Test
    fun `isTurnEnd를 통해 현재 참가자의 턴이 끝났는지 확인할 수 있다`() {
        val notEndParticipant = Player(
            "앙몬드",
            10_000,
            PlayingCard(Suit.SPADES, CardNumber.TWO),
            PlayingCard(Suit.SPADES, CardNumber.THREE)
        )

        assertThat(BlackjackGameTurn(notEndParticipant).isTurnEnd()).isFalse

        val endParticipant = Player(
            "죠르디",
            10_000,
            PlayingCard(Suit.SPADES, CardNumber.ACE),
            PlayingCard(Suit.SPADES, CardNumber.KING)
        )

        assertThat(BlackjackGameTurn(endParticipant).isTurnEnd()).isTrue
    }

    @Test
    fun `hit은 카드를 뽑아 참가자에게 전달한다`() {
        val turn = getBlackjackGameTurn()
        turn.hit(PlayingCards.from(PlayingCard(Suit.DIAMONDS, CardNumber.KING)))

        assertAll(
            { assertThat(turn.participant.hands.cards).hasSize(3) },
            { assertThat(turn.participant.hands.cards).contains(PlayingCard(Suit.DIAMONDS, CardNumber.KING)) }
        )
    }

    @Test
    fun `stay를 통해 카드를 받지 않고 턴을 넘길 것임을 표시할 수 있다`() {
        val turn = getBlackjackGameTurn()
        turn.stay()

        assertThat(turn.participant.isReceivable()).isFalse
    }

    private fun getBlackjackGameTurn(): BlackjackGameTurn {
        val player = Player(
            "스카피",
            10_000,
            PlayingCard(Suit.SPADES, CardNumber.TWO),
            PlayingCard(Suit.SPADES, CardNumber.THREE)
        )

        return BlackjackGameTurn(player)
    }
}

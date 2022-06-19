package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTurnTest {
    @Test
    fun `BlackjackGameTurn은 현재 블랙잭 게임의 턴을 진행할 참가자가 누구인지를 알려 준다`() {
        val participants = Participants(
            dealer = Dealer("딜러", PlayingCard(Suit.SPADES, CardNumber.NINE)),
            players = listOf(
                Player(
                    "강아지",
                    PlayingCard(Suit.SPADES, CardNumber.ACE),
                    PlayingCard(Suit.SPADES, CardNumber.TEN)
                ),
                Player(
                    "고양이",
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
                    PlayingCard(Suit.SPADES, CardNumber.ACE),
                    PlayingCard(Suit.SPADES, CardNumber.TEN)
                ),
                Player(
                    "고양이",
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
            PlayingCard(Suit.SPADES, CardNumber.TWO),
            PlayingCard(Suit.SPADES, CardNumber.THREE)
        )

        assertThat(BlackjackGameTurn.from(notEndParticipant).isTurnEnd()).isFalse

        val endParticipant = Player(
            "죠르디",
            PlayingCard(Suit.SPADES, CardNumber.ACE),
            PlayingCard(Suit.SPADES, CardNumber.KING)
        )

        assertThat(BlackjackGameTurn.from(endParticipant).isTurnEnd()).isTrue
    }
}

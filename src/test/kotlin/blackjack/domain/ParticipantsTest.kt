package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ParticipantsTest {
    private val dealer = Dealer(
        "딜러",
        PlayingCard(Suit.DIAMONDS, CardNumber.TWO),
        PlayingCard(Suit.SPADES, CardNumber.KING)
    )
    private val players = listOf(
        Player(
            "이름",
            10_000,
            PlayingCard(Suit.CLUBS, CardNumber.KING),
            PlayingCard(Suit.SPADES, CardNumber.JACK)
        )
    )
    private val participants = Participants(dealer, players)

    @Test
    fun `Participants는 딜러와 플레이어 목록을 보관한다`() {
        assertAll(
            { assertThat(participants.dealer).isEqualTo(dealer) },
            { assertThat(participants.players).isEqualTo(players) }
        )
    }

    @Test
    fun `all은 딜러와 플레이어들을 하나의 리스트에 담아 반환해 준다`() {
        assertThat(participants.all).contains(dealer, players.first())
    }
}

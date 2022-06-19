package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ParticipantTest {
    @Test
    fun `Participant는 이름과 손패, 점수를 보관한다`() {
        val player = Participant.Player(
            name = "이름"
        )

        assertAll(
            { assertThat(player.name.value).isEqualTo("이름") },
            { assertThat(player.cardsOfHands).isEmpty() },
            { assertThat(player.score).isEqualTo(Score.from(0)) }
        )
    }

    @Test
    fun `receive를 통해 카드를 받아 손패에 추가할 수 있다`() {
        val player = Participant.Player(
            name = "이름"
        )

        player.receive(
            PlayingCards.from(
                listOf(
                    PlayingCard(Suit.CLUBS, CardNumber.JACK),
                    PlayingCard(Suit.CLUBS, CardNumber.ACE)
                )
            )
        )

        assertAll(
            { assertThat(player.cardsOfHands).hasSize(2) }
        )
    }
}

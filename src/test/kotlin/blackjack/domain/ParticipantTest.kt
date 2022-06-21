package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ParticipantTest {
    @Test
    fun `Participant는 이름과 손패, 점수를 보관한다`() {
        val hands = Hands.from(PlayingCards.empty())
        val player = Player(
            name = PlayerName("이름"),
            hands = hands
        )

        assertAll(
            { assertThat(player.name.value).isEqualTo("이름") },
            { assertThat(player.hands).isEqualTo(hands) }
        )
    }

    @Test
    fun `receive를 통해 카드를 받아 손패에 추가할 수 있다`() {
        val player = Player(
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
            { assertThat(player.hands.cards).hasSize(2) }
        )
    }
}

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ParticipantsTest {
    @Test
    fun `참가자들에게 원하는 숫자만큼 카드를 나눌 수 있다`() {
        val participants = Participants(listOf("player1", "player2"))
        participants.addCards(2)

        participants.players.forEach {
            assertThat(it.cards.size).isEqualTo(2)
        }
    }
}

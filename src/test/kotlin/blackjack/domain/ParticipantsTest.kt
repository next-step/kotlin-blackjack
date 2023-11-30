package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러와 게임 플레이어 정보를 모두 반환한다`() {
        // given
        val dealer = Dealer("딜러")
        val players = Players(listOf(Player("player1"), Player("player2")))

        // when
        val participants = Participants(dealer, players).getAll()

        // then
        assertEquals(3, participants.size)
        assertThat(participants == listOf(
            dealer,
            Player("player1"),
            Player("player2")
        ))
    }
}

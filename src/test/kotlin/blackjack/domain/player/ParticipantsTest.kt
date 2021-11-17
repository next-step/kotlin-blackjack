package blackjack.domain.player

import blackjack.domain.card.CardsDeck
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParticipantsTest {

    @Test
    fun `getAllPlayers 를 호출할시 딜러를 포함한 모든 플레이어를 반환한다`() {
        val participants = Participants(
            dealer = Dealer(Participant("딜러")),
            players = listOf(
                Player(Participant("one")),
                Player(Participant("two")),
            ),
            cardsDeck = CardsDeck(),
        )

        val actual = participants.getAllPlayers()

        assertEquals(3, actual.size)
    }
}

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
                Player(Participant("one"), 0),
                Player(Participant("two"), 0),
            ),
        )

        val actual = participants.getAllPlayers()

        assertEquals(3, actual.size)
    }

    @Test
    fun `build 를 이용해 생성 시 딜러를 포함한 모든 플레이어는 2장의 카드를 받는다`() {
        val dealer = Dealer(Participant("딜러"))
        val one = Player(Participant("one"), 0)
        val two = Player(Participant("two"), 0)

        val participants = Participants.build(
            dealer = dealer,
            players = listOf(one, two),
            cardsDeck = CardsDeck(),
        )

        assertEquals(2, dealer.dealer.cards.size)
        assertEquals(2, one.player.cards.size)
        assertEquals(2, two.player.cards.size)
    }
}

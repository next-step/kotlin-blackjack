@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `모든 참가자들은 처음에 2장씩 카드를 받는다`() {
        val dealer = Dealer
        val players = listOf(Player("a"))
        val participants = Participants(dealer, players)

        participants.drawInitialCards()

        participants.value.forEach {
            assertThat(it.cards.get()).hasSize(2)
        }
    }
}

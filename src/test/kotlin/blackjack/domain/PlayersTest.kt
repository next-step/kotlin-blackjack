package blackjack.domain

import blackjack.domain.card.CardPack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayersTest {

    @Test
    fun `모든 player에게 카드를 준다`() {
        val players = Players(listOf(Player("song"), Player("kim")))

        players.giveFirstTwoCardsToAllPlayers(CardPack())

        assertThat(players[0].cardSize).isEqualTo(2)
        assertThat(players[1].cardSize).isEqualTo(2)
    }
}

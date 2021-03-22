package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {

    @Test
    fun `player에게 두 장의 카드씩 준다`() {
        val players = Players(listOf(Player("song"), Player("kim")))
        val dealer = Dealer(players, CardPack())

        val playerDtos = dealer.giveTwoCardsToAllPlayers()
        assertThat(playerDtos).extracting("name").contains("song", "kim")
        assertThat(playerDtos[0].cards).hasSize(2)
        assertThat(playerDtos[1].cards).hasSize(2)
    }

    @Test
    fun `특정 player가 accept하면 카드를 준다`() {
        val players = Players(listOf(Player("song")))
        val dealer = Dealer(players, CardPack())

        val playerDto1 = dealer.giveCard(players[0], false)
        assertThat(playerDto1.cards).hasSize(0)

        val playerDto2 = dealer.giveCard(players[0], true)
        assertThat(playerDto2.cards).hasSize(1)
    }
}

package blackjack.domain

import blackjack.ui.model.PlayerDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DealerTest {

    @Test
    fun `player에게 두 장의 카드씩 준다`() {
        val players = listOf(Player("song"), Player("kim"))
        val dealer = Dealer(players, CardPack())

        val playerDTOs = dealer.giveTwoCardsToAllPlayers()
        Assertions.assertThat(playerDTOs).extracting("name").contains("song", "kim")
        Assertions.assertThat(playerDTOs[0].cards).hasSize(2)
        Assertions.assertThat(playerDTOs[1].cards).hasSize(2)
    }
}

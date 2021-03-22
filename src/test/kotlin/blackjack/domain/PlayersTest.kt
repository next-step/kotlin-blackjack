package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayersTest {
    @Test
    fun `dealer를 players로 추가한다`() {
        val players = Players(mutableListOf(Player("song")))
        val dealer = Dealer(players, CardPack())
        players.addDealerAsPlayer(dealer)

        assertThat(players.toPlayerDtos()).extracting("name").contains(dealer.toPlayerDto().name)
    }
}

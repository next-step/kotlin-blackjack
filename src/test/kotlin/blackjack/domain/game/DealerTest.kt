package blackjack.domain.game

import blackjack.domain.player.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {
    private val dealer = Dealer()
    private val players = Players("a, b, c")

    @BeforeEach
    fun setUp() {
        dealer.initPlayersHand(players)
    }

    @DisplayName("모든 플레이어에게 카드를 2장씩 지급한다.")
    @Test
    fun initialSetting() {
        assertThat(players.list).allSatisfy { it.getCards().size == 2 }
    }

    @DisplayName("한명의 플레이어에게 카드를 지급한다.")
    @Test
    fun giveCardToOne() {
        dealer.giveCardTo(players(0))
        assertThat(players(0).getCards().size).isEqualTo(3)
    }
}

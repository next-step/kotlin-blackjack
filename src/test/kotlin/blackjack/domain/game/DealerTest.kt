package blackjack.domain.game

import blackjack.domain.player.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {
    private val dealer = Dealer()
    private val players = Players.getOrNull("a, b, c")!!

    @BeforeEach
    fun setUp() {
        dealer.initPlayersHand(players)
    }

    @DisplayName("모든 플레이어에게 카드를 2장씩 지급한다.")
    @Test
    fun initialSetting() {
        assertThat(players.participants).allSatisfy { it.getCards().size == 2 }
    }

    @DisplayName("한명의 플레이어에게 카드를 지급한다.")
    @Test
    fun giveCardToOne() {
        dealer.giveCardTo(players(0))
        assertThat(players(0).getCards().size).isEqualTo(3)
    }

    @DisplayName("딜러는 초기에 2장의 카드를 받는다.")
    @Test
    fun receiveOneCard() {
        assertThat(dealer.getCards().size).isEqualTo(2)
    }

    @DisplayName("딜러의 패가 16 이하면 카드를 지급받는다.")
    @Test
    fun receiveMoreCard() {
        dealer.fillHand()
        assertThat(dealer.getScore()).isGreaterThan(16)
    }
}

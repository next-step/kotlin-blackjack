package game.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어")
internal class PlayerTest {

    @Test
    fun `이름으로 플레이어 생성`() {
        val expectedName = "jade"
        val player = Player(expectedName)
        assertThat(player.name).isEqualTo(expectedName)
    }

    @Test
    fun `true 입력하면 상태 그대로`() {
        val expectedName = "jade"
        val player = Player(expectedName)

        player.receive(Card(Suit.SPADE, Denomination.QUEEN))
        player.receive(Card(Suit.SPADE, Denomination.ACE))
        player.determine(true)

        assertThat(player.canReceive()).isTrue
    }

    @Test
    fun `false 입력하면 상태 변화`() {
        val expectedName = "jade"
        val player = Player(expectedName)

        player.receive(Card(Suit.SPADE, Denomination.QUEEN))
        player.receive(Card(Suit.SPADE, Denomination.ACE))
        player.determine(false)

        assertThat(player.canReceive()).isFalse
    }

    @Test
    fun `카드 입력하면 추가`() {
        val expectedName = "jade"
        val player = Player(expectedName)

        player.receive(Card(Suit.SPADE, Denomination.QUEEN))
        player.receive(Card(Suit.SPADE, Denomination.ACE))
        player.determine(false)

        assertThat(player.hand.size()).isEqualTo(2)
    }

    @Test
    fun `카드 받고 21 넘으면 상태 변화`() {
        val expectedName = "jade"
        val player = Player(expectedName)

        player.receive(Card(Suit.SPADE, Denomination.QUEEN))
        player.receive(Card(Suit.SPADE, Denomination.JACK))
        player.receive(Card(Suit.SPADE, Denomination.KING))

        assertThat(player.canReceive()).isFalse
    }

    @Test
    fun `플레이어 버스트로 패배`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(DECK_OF_15)
        dealer.init(DECK_OF_20)
        player.receive(Card(Suit.SPADE, Denomination.JACK))
        assertThat(WinningRecord.getPlayerProfit(player, dealer)).isEqualTo(-1000)
    }

    @Test
    fun `딜러 버스트로 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(DECK_OF_20)
        dealer.init(DECK_OF_15)
        dealer.receive(Card(Suit.HEART, Denomination.JACK))
        assertThat(WinningRecord.getPlayerProfit(player, dealer)).isEqualTo(1000)
    }

    @Test
    fun `블랙잭 무승부`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(DECK_OF_BLACKJACK)
        dealer.init(DECK_OF_BLACKJACK)
        assertThat(WinningRecord.getPlayerProfit(player, dealer)).isEqualTo(0)
    }

    @Test
    fun `플레이어가 블랙잭으로 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(DECK_OF_BLACKJACK)
        dealer.init(DECK_OF_20)
        assertThat(WinningRecord.getPlayerProfit(player, dealer)).isEqualTo(1500)
    }

    @Test
    fun `딜러가 블랙잭으로 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(DECK_OF_20)
        dealer.init(DECK_OF_BLACKJACK)
        assertThat(WinningRecord.getPlayerProfit(player, dealer)).isEqualTo(-1000)
    }

    @Test
    fun `둘다 21이하, 스코어 비교로 플레이어 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(DECK_OF_15)
        dealer.init(DECK_OF_10)
        assertThat(WinningRecord.getPlayerProfit(player, dealer)).isEqualTo(1000)
    }

    @Test
    fun `둘다 21이하, 스코어 비교로 딜러 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(DECK_OF_10)
        dealer.init(DECK_OF_15)
        assertThat(WinningRecord.getPlayerProfit(player, dealer)).isEqualTo(-1000)
    }

    @Test
    fun `둘다 21이하, 스코어 비교로 무승부`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(DECK_OF_15)
        dealer.init(DECK_OF_15)
        assertThat(WinningRecord.getPlayerProfit(player, dealer)).isEqualTo(0)
    }

    companion object {
        private val DECK_OF_BLACKJACK = listOf(Card(Suit.SPADE, Denomination.ACE), Card(Suit.SPADE, Denomination.KING))
        private val DECK_OF_20 = listOf(Card(Suit.CLOVER, Denomination.KING), Card(Suit.CLOVER, Denomination.JACK))
        private val DECK_OF_15 = listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.FIVE))
        private val DECK_OF_10 = listOf(Card(Suit.DIAMOND, Denomination.SEVEN), Card(Suit.DIAMOND, Denomination.THREE))
    }
}

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

        assertThat(player.cards.size()).isEqualTo(2)
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
        player.init(listOf(Card(Suit.SPADE, Denomination.FIVE), Card(Suit.SPADE, Denomination.TEN)))
        dealer.init(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.FIVE)))
        player.receive(Card(Suit.SPADE, Denomination.JACK))
        assertThat(player.getProfit(dealer)).isEqualTo(-1000)
    }

    @Test
    fun `딜러 버스트로 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(listOf(Card(Suit.SPADE, Denomination.ACE), Card(Suit.SPADE, Denomination.QUEEN)))
        dealer.init(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.QUEEN)))
        dealer.receive(Card(Suit.HEART, Denomination.JACK))
        assertThat(player.getProfit(dealer)).isEqualTo(1000)
    }

    @Test
    fun `블랙잭 무승부`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(listOf(Card(Suit.SPADE, Denomination.ACE), Card(Suit.SPADE, Denomination.QUEEN)))
        dealer.init(listOf(Card(Suit.SPADE, Denomination.ACE), Card(Suit.SPADE, Denomination.QUEEN)))
        assertThat(player.getProfit(dealer)).isEqualTo(0)
    }

    @Test
    fun `플레이어가 블랙잭으로 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(listOf(Card(Suit.SPADE, Denomination.ACE), Card(Suit.SPADE, Denomination.QUEEN)))
        dealer.init(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.TEN)))
        assertThat(player.getProfit(dealer)).isEqualTo(1500)
    }

    @Test
    fun `딜러가 블랙잭으로 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(listOf(Card(Suit.SPADE, Denomination.ACE), Card(Suit.SPADE, Denomination.FIVE)))
        dealer.init(listOf(Card(Suit.HEART, Denomination.ACE), Card(Suit.HEART, Denomination.QUEEN)))
        assertThat(player.getProfit(dealer)).isEqualTo(-1000)
    }

    @Test
    fun `둘다 21이하, 스코어 비교로 플레이어 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(listOf(Card(Suit.SPADE, Denomination.KING), Card(Suit.SPADE, Denomination.JACK)))
        dealer.init(listOf(Card(Suit.HEART, Denomination.FIVE), Card(Suit.HEART, Denomination.QUEEN)))
        assertThat(player.getProfit(dealer)).isEqualTo(1000)
    }

    @Test
    fun `둘다 21이하, 스코어 비교로 딜러 승리`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(listOf(Card(Suit.HEART, Denomination.FIVE), Card(Suit.HEART, Denomination.QUEEN)))
        dealer.init(listOf(Card(Suit.SPADE, Denomination.KING), Card(Suit.SPADE, Denomination.JACK)))
        assertThat(player.getProfit(dealer)).isEqualTo(-1000)
    }

    @Test
    fun `둘다 21이하, 스코어 비교로 무승부`() {
        val player = Player("jade", 1000)
        val dealer = Dealer()
        player.init(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.HEART, Denomination.JACK)))
        dealer.init(listOf(Card(Suit.SPADE, Denomination.KING), Card(Suit.SPADE, Denomination.JACK)))
        assertThat(player.getProfit(dealer)).isEqualTo(0)
    }
}

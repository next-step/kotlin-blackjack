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
    fun `y 입력하면 상태 그대로`() {
        val expectedName = "jade"
        val player = Player(expectedName)

        player.receive(Card(Suit.SPADE, Denomination.QUEEN))
        player.receive(Card(Suit.SPADE, Denomination.ACE))
        player.determine("y")

        assertThat(player.canReceive()).isTrue
    }

    @Test
    fun `n 입력하면 상태 변화`() {
        val expectedName = "jade"
        val player = Player(expectedName)

        player.receive(Card(Suit.SPADE, Denomination.QUEEN))
        player.receive(Card(Suit.SPADE, Denomination.ACE))
        player.determine("n")

        assertThat(player.canReceive()).isFalse
    }

    @Test
    fun `카드 입력하면 추가`() {
        val expectedName = "jade"
        val player = Player(expectedName)

        player.receive(Card(Suit.SPADE, Denomination.QUEEN))
        player.receive(Card(Suit.SPADE, Denomination.ACE))
        player.determine("n")

        assertThat(player.cards).hasSize(2)
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
}

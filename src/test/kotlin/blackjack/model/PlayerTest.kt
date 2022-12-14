package blackjack.model

import blackjack.model.Denomination.ACE
import blackjack.model.Denomination.JACK
import blackjack.model.Denomination.QUEEN
import blackjack.model.Denomination.TEN
import blackjack.model.Suit.SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    internal fun `플레이어 이름을 입력받는다`() {
        val name = "jason"
        val player = Player(name)
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    internal fun `카드를 뽑으면 플레이어 카드목록에 추가된다`() {
        val player = Player("jason")
        player.addCard(Card(SPADE, TEN))
        player.addCard(Card(SPADE, QUEEN))
        assertThat(player.cards.size).isEqualTo(2)
    }

    @Test
    internal fun `플레이어가 카드를 뽑을 수 있는지 판단한다`() {
        val player = Player("jason")
        player.addCard(Card(SPADE, TEN))
        player.addCard(Card(SPADE, QUEEN))
        assertThat(player.isPickable()).isTrue
    }

    @Test
    internal fun `카드 합이 21을 넘을 경우 카드를 얻지 못한다`() {
        val player = Player("jason")
        player.addCard(Card(SPADE, ACE))
        player.addCard(Card(SPADE, JACK))
        player.addCard(Card(SPADE, QUEEN))
        assertThat(player.isPickable()).isFalse
        assertThrows<IllegalStateException> { player.addCard(Card(SPADE, ACE)) }
    }
}

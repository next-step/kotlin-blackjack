package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player("이름1")
        assertThat(player.name).isEqualTo("이름1")
    }

    @Test
    fun `플레이어가 카드를 받고 저장한다`() {
        val player = Player("이름1")
        val card = Card.of(Suit.HEART, Rank.ACE)
        player.receiveCard(card)
        assertThat(player.cards).contains(card)
    }
}

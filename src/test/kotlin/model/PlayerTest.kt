package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    @DisplayName("플레이어는 카드 목록 가질 수 있다")
    fun `hasACards`() {
        val name = PlayerName("hello")
        val player = Player(name)
        val card = Card(Suit.CLUBS, Denomination.ACE)
        player.receive(card)
        assertThat(player.cards).hasSize(1)
    }

    @Test
    @DisplayName("플레이어는 이름을 가질 수 있다")
    fun `hasAName`() {
        val name = PlayerName("hello")
        val player = Player(name)
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    @DisplayName("가지고 있는 카드의 스코어를 합산 된다")
    fun `scoreOfCard`() {
        val name = PlayerName("hello")
        var player = Player(name)
        val cards = listOf<Card>(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.QUEEN))
        receiveCard(player, cards)
        assertThat(player.score()).isEqualTo(21)
    }

    @Test
    @DisplayName("가지고 있는 카드에 ACE 가 있으면 11점이 된다")
    fun `scoreOfCardAce`() {
        val name = PlayerName("hello")
        val player = Player(name)
        player.receive(Card(Suit.CLUBS, Denomination.ACE))
        assertThat(player.score()).isGreaterThanOrEqualTo(11)
    }

    private fun receiveCard(player: Player, list:List<Card>) {
        for(card in list) {
            player.receive(card)
        }
    }
}

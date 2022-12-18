package blackjack.domain

import blackjack.domain.card.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    @DisplayName("최초 등록시 플레이어가 들고 있는 카드는 없음")
    fun `No cards held by the player at initial registration`() {
        val player = Player("홍길동")

        assertThat(player.cards).isEmpty()
    }

    @Test
    @DisplayName("플레이어가 카드 한장을 받으면 카드를 1장 가지고 있음")
    fun `Players have one card when they receive one card`() {
        val card = Deck().draw()
        val player = Player("홍길동")
        player.enroll(card)

        assertThat(player.cards.size).isEqualTo(1)
    }

    @Test
    @DisplayName("플레이어 등록시 이름이 홍길동이면 홍길동")
    fun `If the name is 홍길동 when registering a player, it is 홍길동`() {
        val player = Player("홍길동")

        assertThat(player.name).isEqualTo("홍길동")
    }
}

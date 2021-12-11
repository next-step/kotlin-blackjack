package blackjack.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `원하는 이름과 카드리스트를 가진 플레이어 객체가 생성되는지 확인한다`() {

        // given
        val cards = mutableListOf(
            Card("SPADES", 9),
            Card("SPADES", 1)
        )
        val name = "King"

        // then
        val player = Player(name, cards)

        // when

        assertThat(player.name).isEqualTo(name)
        assertThat(player.cards).isEqualTo(cards)
    }

    @Test
    fun `플레이어가 draw 행위를 할떄 카드 리스트에 카드가 추가되는지 확인한다`() {

        // given
        val cards = mutableListOf(
            Card("SPADES", 9),
            Card("SPADES", 1)
        )
        val name = "King"
        val player = Player(name, cards)

        val drawCard = Card("SPADES", 5)

        // then
        player.draw(drawCard)

        assertThat(player.cards).contains(drawCard)
    }

    @Test
    fun `플레이어가 stay 행위를 할때 기존 카드가 유지되는지 확인한다`() {

        // given
        val cards = mutableListOf(
            Card("SPADES", 9),
            Card("SPADES", 1)
        )
        val name = "King"
        val player = Player(name, cards)

        // then
        player.stay()

        assertThat(player.cards).isEqualTo(cards)
    }
}

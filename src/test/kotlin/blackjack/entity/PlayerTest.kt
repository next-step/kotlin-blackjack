package blackjack.entity

import blackjack.entity.enums.Denomination
import blackjack.entity.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `원하는 이름과 카드리스트를 가진 플레이어 객체가 생성되는지 확인한다`() {

        // given
        val cards = mutableListOf(
            Card(Suit.SPADE, Denomination.NINE),
            Card(Suit.SPADE, Denomination.ACE)
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
            Card(Suit.SPADE, Denomination.NINE),
            Card(Suit.SPADE, Denomination.ACE)
        )
        val name = "King"
        val player = Player(name, cards)

        val drawCard = Card(Suit.SPADE, Denomination.FIVE)

        // then
        player.hits(drawCard)

        assertThat(player.cards).contains(drawCard)
    }

    @Test
    fun `플레이어가 stay 행위를 할때 기존 카드가 유지되는지 확인한다`() {

        // given
        val cards = mutableListOf(
            Card(Suit.SPADE, Denomination.NINE),
            Card(Suit.SPADE, Denomination.ACE)
        )
        val name = "King"
        val player = Player(name, cards)

        // then
        player.stay()

        assertThat(player.cards).isEqualTo(cards)
    }
}

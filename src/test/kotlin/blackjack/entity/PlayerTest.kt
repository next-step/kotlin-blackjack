package blackjack.entity

import blackjack.domain.entity.Card
import blackjack.domain.entity.Player
import blackjack.domain.entity.enums.Denomination
import blackjack.domain.entity.enums.Suit
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
    fun `플레이어가 hits 행위를 할떄 카드 리스트에 카드가 추가되는지 확인한다`() {

        // given
        val cards = mutableListOf(
            Card(Suit.SPADE, Denomination.NINE),
            Card(Suit.SPADE, Denomination.ACE)
        )
        val name = "King"
        val player = Player(name, cards)

        val beforeCardSize = cards.size

        // then
        player.hits()

        assertThat(player.cards.size).isEqualTo(beforeCardSize + 1)
    }
}

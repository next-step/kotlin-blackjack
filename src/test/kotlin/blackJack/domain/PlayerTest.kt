package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `플레이어는 이름이 지정되어 있고, 카드를 가지고 있다`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.HEARTS, Denomination.ACE),
                Card(Suit.SPADES, Denomination.KING)
            )
        )
        val player = Player("김형준", cards)

        // then
        assertAll({
            assertThat(player.name).isEqualTo("김형준")
            assertThat(player.cards).isEqualTo(cards)
        })
    }
}

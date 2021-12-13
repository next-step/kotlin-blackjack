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

        val drawCard = Card(Suit.CLUB, Denomination.FIVE)

        // then
        player.hits(drawCard)

        assertThat(player.cards.size).isEqualTo(beforeCardSize + 1)
    }

    @Test
    fun `플레이어의 카드가 2,5,4,8,ACE,ACE 일때 블랙잭이 나오는지 확인 합니다`() {
        val player = Player(
            "kim",
            mutableListOf(
                Card(Suit.SPADE, Denomination.TWO),
                Card(Suit.SPADE, Denomination.FIVE),
                Card(Suit.SPADE, Denomination.FOUR),
                Card(Suit.SPADE, Denomination.EIGHT),
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE)
            )
        )
        assertThat(player.scoreCalculation()).isEqualTo(21)
    }

    @Test
    fun `플레이어의 카드가 10,ACE 일때 블랙잭이 나오는지 확인 합니다`() {
        val player = Player(
            "kim",
            mutableListOf(
                Card(Suit.SPADE, Denomination.TEN),
                Card(Suit.SPADE, Denomination.ACE)
            )
        )
        assertThat(player.scoreCalculation()).isEqualTo(21)
    }

    @Test
    fun `플레이어의 카드가 7,8,ACE 일때 에이스가 1로 변환되어 16이 나오는지 확인합니다`() {
        val player = Player(
            "kim",
            mutableListOf(
                Card(Suit.SPADE, Denomination.SEVEN),
                Card(Suit.SPADE, Denomination.EIGHT),
                Card(Suit.SPADE, Denomination.ACE)
            )
        )
        assertThat(player.scoreCalculation()).isEqualTo(16)
    }
}

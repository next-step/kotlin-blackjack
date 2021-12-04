package blackjack

import blackjack.domain.Card
import blackjack.domain.Name
import blackjack.domain.NumberType
import blackjack.domain.Player
import blackjack.domain.SuitType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `player의 score가 21점 미만이면 카드를 추가할 수 있다`() {
        val player = Player.of(Name.from("seunghwan"))
        val card1 = Card(SuitType.SPADE, NumberType.TEN)
        val card2 = Card(SuitType.SPADE, NumberType.TEN)
        val card3 = Card(SuitType.SPADE, NumberType.TWO)

        player.hit(card1)
        player.hit(card2)
        player.hit(card3)

        assertThat(player.cards.cards).containsExactlyElementsOf(
            listOf(
                Card(SuitType.SPADE, NumberType.TEN),
                Card(SuitType.SPADE, NumberType.TEN),
                Card(SuitType.SPADE, NumberType.TWO)
            )
        )
    }

    @Test
    fun `player의 score가 21점 이상이면 카드를 추가할 수 없다`() {
        val player = Player.of(Name.from("seunghwan"))
        val card1 = Card(SuitType.SPADE, NumberType.TEN)
        val card2 = Card(SuitType.SPADE, NumberType.TEN)
        val card3 = Card(SuitType.SPADE, NumberType.TWO)

        player.hit(card1)
        assertThat(player.canHit()).isTrue

        player.hit(card2)
        assertThat(player.canHit()).isTrue

        player.hit(card3)
        assertThat(player.canHit()).isFalse
    }

    @Test
    fun `player는 가지고있는 카드들의 점수를 알고있다(nowScore())`() {
        val player = Player.of(Name.from("seunghwan"))
        val card1 = Card(SuitType.SPADE, NumberType.ACE)
        val card2 = Card(SuitType.SPADE, NumberType.ACE)
        val card3 = Card(SuitType.SPADE, NumberType.ACE)

        player.hit(card1)
        player.hit(card2)
        player.hit(card3)
        assertThat(player.score).isEqualTo(13)
    }

    @Test
    fun `Player는 Bust 되었는지 알 수 있다`() {
        val player = Player.of(name = Name.from("player"))
        player.hit(Card(SuitType.SPADE, NumberType.TEN))
        player.hit(Card(SuitType.SPADE, NumberType.TEN))
        player.hit(Card(SuitType.SPADE, NumberType.TEN))

        assertThat(player.isBust()).isTrue
    }
}

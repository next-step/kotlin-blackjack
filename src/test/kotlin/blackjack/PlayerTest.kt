package blackjack

import blackjack.domain.Card
import blackjack.domain.Name
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.SuitType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `player의 score가 21점 미만이면 카드를 추가할 수 있다`() {
        val player = Player.of(Name.from("seunghwan"))
        val card1 = Card(SuitType.SPADE, Denomination.TEN)
        val card2 = Card(SuitType.SPADE, Denomination.TEN)
        val card3 = Card(SuitType.SPADE, Denomination.TWO)

        player.hit(card1)
        player.hit(card2)
        player.hit(card3)

        assertThat(player.cards.cards).containsExactlyElementsOf(
            listOf(
                Card(SuitType.SPADE, Denomination.TEN),
                Card(SuitType.SPADE, Denomination.TEN),
                Card(SuitType.SPADE, Denomination.TWO)
            )
        )
    }

    @Test
    fun `player의 score가 21점 이상이면 카드를 추가할 수 없다`() {
        val player = Player.of(Name.from("seunghwan"))
        val card1 = Card(SuitType.SPADE, Denomination.TEN)
        val card2 = Card(SuitType.SPADE, Denomination.TEN)
        val card3 = Card(SuitType.SPADE, Denomination.TWO)

        player.hit(card1)
        assertThat(player.canHit()).isTrue

        player.hit(card2)
        assertThat(player.canHit()).isTrue

        player.hit(card3)
        assertThat(player.canHit()).isFalse
    }

    @Test
    fun `player는 가지고있는 카드들의 점수를 알고있다`() {
        val player = Player.of(Name.from("seunghwan"))
        val card1 = Card(SuitType.SPADE, Denomination.ACE)
        val card2 = Card(SuitType.SPADE, Denomination.ACE)
        val card3 = Card(SuitType.SPADE, Denomination.ACE)

        player.hit(card1)
        player.hit(card2)
        player.hit(card3)
        assertThat(player.score.score).isEqualTo(13)
    }

    @Test
    fun `Player는 Bust 되었는지 알 수 있다`() {
        val player = Player.of(name = Name.from("player"))
        player.hit(Card(SuitType.SPADE, Denomination.TEN))
        player.hit(Card(SuitType.SPADE, Denomination.TEN))
        player.hit(Card(SuitType.SPADE, Denomination.TEN))

        assertThat(player.isBust()).isTrue
    }
}

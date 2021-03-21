package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `카드를 받아서 현재 숫자를 계산한다(2클로버)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.CLOVER, CardType.TWO))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(CardType.TWO.point)
    }

    @Test
    fun `Ace 한 개를 포함한 카드를 받아서 현재 숫자를 계산한다(Ace를 11로 계산)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.CLOVER, CardType.TWO))
        player.takeCard(Card(CardShape.CLOVER, CardType.ACE))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(13)
    }

    @Test
    fun `Ace 한 개를 포함한 카드를 받아서 현재 숫자를 계산한다(Ace를 1로 계산)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.CLOVER, CardType.TWO))
        player.takeCard(Card(CardShape.CLOVER, CardType.TEN))
        player.takeCard(Card(CardShape.CLOVER, CardType.ACE))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(13)
    }

    @Test
    fun `Ace 두 개를 포함한 카드를 받아서 현재 숫자를 계산한다(둘 다 1로 계산)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.CLOVER, CardType.ACE))
        player.takeCard(Card(CardShape.HEART, CardType.ACE))
        player.takeCard(Card(CardShape.CLOVER, CardType.TEN))
        player.takeCard(Card(CardShape.CLOVER, CardType.TWO))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(14)
    }

    @Test
    fun `Ace 두 개를 포함한 카드를 받아서 현재 숫자를 계산한다(하나는 11, 하나는 1로 계산)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.CLOVER, CardType.ACE))
        player.takeCard(Card(CardShape.HEART, CardType.ACE))
        player.takeCard(Card(CardShape.CLOVER, CardType.FIVE))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(17)
    }

    @Test
    fun `점수가 21점 이상이면, 더 이상 카드를 받을 수 없다`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.CLOVER, CardType.TEN))
        player.takeCard(Card(CardShape.HEART, CardType.TEN))
        player.takeCard(Card(CardShape.CLOVER, CardType.TEN))

        assertThatThrownBy { player.takeCard(Card(CardShape.CLOVER, CardType.TEN)) }
            .isInstanceOf(IllegalStateException::class.java)
    }
}

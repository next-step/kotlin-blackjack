package blackjack.domain

import blackjack.enums.CardShape
import blackjack.enums.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `카드를 받아서 현재 숫자를 계산한다(2클로버)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.Clover, CardType.Two))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(CardType.Two.point)
    }
    
    @Test
    fun `Ace 한 개를 포함한 카드를 받아서 현재 숫자를 계산한다(Ace를 11로 계산)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.Clover, CardType.Two))
        player.takeCard(Card(CardShape.Clover, CardType.Ace))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(13)
    }

    @Test
    fun `Ace 한 개를 포함한 카드를 받아서 현재 숫자를 계산한다(Ace를 1로 계산)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.Clover, CardType.Two))
        player.takeCard(Card(CardShape.Clover, CardType.Ten))
        player.takeCard(Card(CardShape.Clover, CardType.Ace))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(13)
    }

    @Test
    fun `Ace 두 개를 포함한 카드를 받아서 현재 숫자를 계산한다(둘 다 1로 계산)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.Clover, CardType.Ace))
        player.takeCard(Card(CardShape.Heart, CardType.Ace))
        player.takeCard(Card(CardShape.Clover, CardType.Ten))
        player.takeCard(Card(CardShape.Clover, CardType.Two))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(14)
    }

    @Test
    fun `Ace 두 개를 포함한 카드를 받아서 현재 숫자를 계산한다(하나는 11, 하나는 1로 계산)`() {
        val player = Player("song")
        player.takeCard(Card(CardShape.Clover, CardType.Ace))
        player.takeCard(Card(CardShape.Heart, CardType.Ace))
        player.takeCard(Card(CardShape.Clover, CardType.Five))
        val cardSum = player.calculateCardSum()
        assertThat(cardSum).isEqualTo(17)
    }
}

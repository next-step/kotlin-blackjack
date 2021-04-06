package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `카드를 받아서 현재 숫자를 계산한다(2클로버)`() {
        val player = Player("song", getCardSetOfClover(CardType.TWO))
        val cardSum = player.cardPointSum()
        assertThat(cardSum).isEqualTo(CardType.TWO.point)
    }

    @Test
    fun `Ace 한 개를 포함한 카드를 받아서 현재 숫자를 계산한다(Ace를 11로 계산)`() {
        val player = Player("song", getCardSetOfClover(CardType.TWO, CardType.ACE))
        val cardSum = player.cardPointSum()
        assertThat(cardSum).isEqualTo(13)
    }

    @Test
    fun `Ace 한 개를 포함한 카드를 받아서 현재 숫자를 계산한다(Ace를 1로 계산)`() {
        val player = Player("song", getCardSetOfClover(CardType.TWO, CardType.TEN, CardType.ACE))
        val cardSum = player.cardPointSum()
        assertThat(cardSum).isEqualTo(13)
    }

    @Test
    fun `Ace 두 개를 포함한 카드를 받아서 현재 숫자를 계산한다(둘 다 1로 계산)`() {
        val player = Player("song", getCardSetOfClover(CardType.ACE, CardType.ACE, CardType.TEN, CardType.TWO))

        val cardSum = player.cardPointSum()
        assertThat(cardSum).isEqualTo(14)
    }

    @Test
    fun `Ace 두 개를 포함한 카드를 받아서 현재 숫자를 계산한다(하나는 11, 하나는 1로 계산)`() {
        val player = Player("song", getCardSetOfClover(CardType.ACE, CardType.FIVE, CardType.ACE))

        val cardSum = player.cardPointSum()
        assertThat(cardSum).isEqualTo(17)
    }

    @Test
    fun `점수가 21점 이상이면, 더 이상 카드를 받을 수 없다`() {
        val player = Player("song", getCardSetOfClover(CardType.TEN, CardType.JACK, CardType.QUEEN))

        assertThatThrownBy { player.takeCard(Card(CardShape.CLOVER, CardType.TEN)) }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `처음 2장의 카드를 받는다 (블랙잭이 아닌 경우)`() {
        val player = Player("song")
        player.takeFirstTwoCards(Card(CardShape.CLOVER, CardType.ACE), Card(CardShape.CLOVER, CardType.ACE))

        assertThat(player.isBlackjack).isFalse()
    }

    @Test
    fun `처음 2장의 카드를 받는다 (블랙잭인 경우)`() {
        val player = Player("song")
        player.takeFirstTwoCards(Card(CardShape.CLOVER, CardType.ACE), Card(CardShape.CLOVER, CardType.JACK))

        assertThat(player.isBlackjack).isTrue()
    }

    fun getCardSetOfClover(vararg cardTypes: CardType): MutableSet<Card> =
        cardTypes.map { Card(CardShape.CLOVER, it) }.toMutableSet()
}

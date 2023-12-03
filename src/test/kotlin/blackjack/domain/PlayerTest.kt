@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        val card = Card(Denomination.ACE, Suit.SPADE)
        val player = Player("a")

        player.receiveCard(card)

        assertThat(player.cards.get()).containsExactly(card)
    }

    @Test
    fun `플레이어가 가진 카드 숫자의 총합이 21 초과이면 버스트이다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.TWO, Suit.SPADE)
        val player = Player("a")

        player.run {
            receiveCard(card1)
            receiveCard(card2)
            receiveCard(card3)
        }

        val actual = player.isBust()

        assertThat(actual).isTrue()
    }

    @Test
    fun `플레이어가 가진 카드 숫자의 총합이 21이면 블랙잭이다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.ACE, Suit.SPADE)
        val player = Player("a")

        player.run {
            receiveCard(card1)
            receiveCard(card2)
            receiveCard(card3)
        }

        val actual = player.isBlackjack()

        assertThat(actual).isTrue()
    }

    @Test
    fun `플레이어가 버스트, 블랙잭이 아니면 카드를 더 받을 수 있다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val player = Player("a")

        player.run {
            receiveCard(card1)
            receiveCard(card2)
        }

        val actual = player.canReceiveCard()

        assertThat(actual).isTrue()
    }

    @Test
    fun `플레이어가 버스트이면 카드를 더 받을 수 없다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.TWO, Suit.DIAMOND)
        val player = Player("a")

        player.run {
            receiveCard(card1)
            receiveCard(card2)
            receiveCard(card3)
        }

        val actual = player.canReceiveCard()

        assertThat(actual).isFalse()
    }

    @Test
    fun `플레이어가 블랙잭이면 카드를 더 받을 수 없다`() {
        val card1 = Card(Denomination.TEN, Suit.SPADE)
        val card2 = Card(Denomination.TEN, Suit.DIAMOND)
        val card3 = Card(Denomination.ACE, Suit.DIAMOND)
        val player = Player("a")

        player.run {
            receiveCard(card1)
            receiveCard(card2)
            receiveCard(card3)
        }

        val actual = player.canReceiveCard()

        assertThat(actual).isFalse()
    }
}

package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PlayerTest {

    @Test
    fun `점수가 21점을 넘으면 죽는다`() {
        val player = Player("kasha", 1)
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.JACK))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.QUEEN))

        assertTrue(player.isBust())
    }

    @Test
    fun `베팅 금액이 0보다 작을 경우 예외가 발생합니다`() {
        assertThrows<IllegalArgumentException> {
            Player("kasha", -1000)
        }
    }

    @Test
    fun `카드가 2장이면서 21점이면 보상은 1,5배이다`() {
        val player = Player("kasha", 3000)
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.ACE))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        val dealer = Dealer()
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.SEVEN))
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.TEN))
        val users = Users(listOf(dealer, player))

        assertThat(player.getEvaluate(users)).isEqualTo(4500)
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭일 경우 베팅액을 돌려 받는다`() {
        val player = Player("kasha", 3000)
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.ACE))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        val dealer = Dealer()
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.ACE))
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.TEN))
        val users = Users(listOf(dealer, player))

        assertThat(player.getEvaluate(users)).isEqualTo(0)
    }

    @Test
    fun `플레이어가 딜러를 이겼을 경우 베팅액을 받는다`() {
        val price = 3000
        val player = Player("kasha", price)
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.SEVEN))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        val dealer = Dealer()
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.TWO))
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.TEN))
        val users = Users(listOf(dealer, player))

        assertThat(player.getEvaluate(users)).isEqualTo(price)
    }

    @Test
    fun `플레이어가 딜러에게 졌을 경우 베팅액을 잃는다`() {
        val price = 3000
        val player = Player("kasha", price)
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.TWO))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        val dealer = Dealer()
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.SEVEN))
        dealer.cardDeck.add(Card(CardType.HEART, CardNumber.TEN))
        val users = Users(listOf(dealer, player))

        assertThat(player.getEvaluate(users)).isEqualTo(price.unaryMinus())
    }
}

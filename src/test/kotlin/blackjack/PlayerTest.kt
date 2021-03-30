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
}

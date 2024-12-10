package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSuit
import blackjack.domain.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 가진 카드의 숫자의 총합이 21보다 낮으면 카드를 더 뽑을 수 있는 상태이다`() {
        val player = Player("test")

        player.addCard(Card(CardNumber.JACK, CardSuit.HEART))
        player.addCard(Card(CardNumber.QUEEN, CardSuit.HEART))

        player.calculateCardScore()
        player.findEnabledMoreCard() shouldBe true
    }

    @Test
    fun `플레이어가 가진 카드의 숫자의 총합이 21이면 카드를 더 뽑을 수 있는 상태이다`() {
        val player = Player("test")

        player.addCard(Card(CardNumber.JACK, CardSuit.HEART))
        player.addCard(Card(CardNumber.ACE, CardSuit.HEART))

        player.calculateCardScore()
        player.findEnabledMoreCard() shouldBe true
    }

    @Test
    fun `플레이어가 가진 카드의 숫자의 총합이 21보다 크면 카드를 더 뽑을 수 없는 상태이다`() {
        val player = Player("test")

        player.addCard(Card(CardNumber.JACK, CardSuit.HEART))
        player.addCard(Card(CardNumber.QUEEN, CardSuit.HEART))
        player.addCard(Card(CardNumber.ACE, CardSuit.HEART))
        player.addCard(Card(CardNumber.ACE, CardSuit.HEART))

        player.calculateCardScore()
        player.findEnabledMoreCard() shouldBe false
    }

    @Test
    fun `플레이어가 가진 카드의 숫자의 총합이 21에 가장 가까운 경우의 숫자를 찾을 수 있다`() {
        val player = Player("test")

        player.addCard(Card(CardNumber.JACK, CardSuit.HEART))
        player.addCard(Card(CardNumber.ACE, CardSuit.HEART))

        player.calculateCardScore()
        player.deck.score shouldBe 21
    }
}

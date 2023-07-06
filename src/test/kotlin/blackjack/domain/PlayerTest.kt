package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    context("플레이어는 카드를 추가할 수 있다.") {
        val player = Player("june")
        val card = Card(Suit.SPADE, Denomination.ACE)
        player.addCard(card)
        player.cards.size shouldBe 1
        player.cards.contains(card) shouldBe true
    }

    context("플레이어는 카드가 21점을 초과하지 않으면 카드를 더 뽑을 수 있다.") {
        val player = Player("june")
        player.addCard(Card(Suit.SPADE, Denomination.ACE))
        player.addCard(Card(Suit.SPADE, Denomination.TWO))
        player.canDrawMoreCard() shouldBe true
    }

    context("플레이어는 카드가 21점 이상이면 카드를 더 뽑을 수 없다.") {
        val player = Player("june")
        player.addCard(Card(Suit.SPADE, Denomination.JACK))
        player.addCard(Card(Suit.SPADE, Denomination.NINE))
        player.addCard(Card(Suit.SPADE, Denomination.TWO))
        player.canDrawMoreCard() shouldBe false
    }

    context("ACE를 11로 계산해서 21점이 되면 카드를 더 뽑을 수 없다.") {
        val player = Player("june")
        player.addCard(Card(Suit.SPADE, Denomination.ACE))
        player.addCard(Card(Suit.SPADE, Denomination.TEN))
        player.canDrawMoreCard() shouldBe false
    }
})

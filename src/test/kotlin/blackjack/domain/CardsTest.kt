package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({
    context("플레이어는 카드가 21점을 초과하지 않으면 카드를 더 뽑을 수 있다.") {
        val cards = Cards()
        cards.add(Card(Suit.SPADE, Denomination.ACE))
        cards.add(Card(Suit.SPADE, Denomination.TWO))
        cards.canDrawMoreCard() shouldBe true
    }

    context("플레이어는 카드가 21점 이상이면 카드를 더 뽑을 수 없다.") {
        val cards = Cards()
        cards.add(Card(Suit.SPADE, Denomination.JACK))
        cards.add(Card(Suit.SPADE, Denomination.NINE))
        cards.add(Card(Suit.SPADE, Denomination.TWO))
        cards.canDrawMoreCard() shouldBe false
    }

    context("ACE를 11로 계산해서 21점이 되면 카드를 더 뽑을 수 없다.") {
        val cards = Cards()
        cards.add(Card(Suit.SPADE, Denomination.ACE))
        cards.add(Card(Suit.SPADE, Denomination.TEN))
        cards.canDrawMoreCard() shouldBe false
    }
})

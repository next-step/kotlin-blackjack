package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DealerTest : FunSpec({
    context("딜러는 16점 이하이면 카드를 추가로 받는다") {
        val dealer = Dealer()
        dealer.addCard(Card(Suit.HEART, Denomination.TEN))
        dealer.addCard(Card(Suit.HEART, Denomination.SIX))

        dealer.canDrawMoreCard() shouldBe true
    }

    context("딜러는 17점 이상이면 카드를 추가로 받을 수 없다.") {
        val dealer = Dealer()
        dealer.addCard(Card(Suit.HEART, Denomination.TEN))
        dealer.addCard(Card(Suit.HEART, Denomination.SEVEN))

        dealer.canDrawMoreCard() shouldBe false
    }
})

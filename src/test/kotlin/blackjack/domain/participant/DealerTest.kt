package blackjack.domain.participant

import blackjack.domain.Hand
import blackjack.domain.card.CardNumber
import blackjack.test.TestObjectGenerator
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "게임이 시작하면 두 장의 카드를 받는다." {
        val dealer = Dealer()
        dealer.start { TestObjectGenerator.card() }

        dealer.cards().size shouldBe 2
    }

    "가지고 있는 카드 2장의 점수가 16이하면 1장을 추가로 받는다" {
        val hand = Hand.init.add(
            TestObjectGenerator.cards(CardNumber.TEN, CardNumber.SIX)
        )
        val dealer = Dealer(hand)

        dealer.play { TestObjectGenerator.card() }

        dealer.cards().size shouldBe 3
    }

    "가지고 있는 카드 2장의 점수가 17이상이면 카드를 받지 않는다." {
        val hand = Hand.init.add(
            TestObjectGenerator.cards(CardNumber.TEN, CardNumber.SEVEN)
        )
        val dealer = Dealer(hand)

        dealer.play { TestObjectGenerator.card() }

        dealer.cards().size shouldBe 2
    }

    "카드를 최대 3장까지 가질 수 있다." {
        val hand = Hand.init.add(
            TestObjectGenerator.cards(CardNumber.TWO, CardNumber.TWO)
        )
        val dealer = Dealer(hand)

        dealer.play { TestObjectGenerator.card(CardNumber.TWO) }

        dealer.cards().size shouldBe 3
    }
})

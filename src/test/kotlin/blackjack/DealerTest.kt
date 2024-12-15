package blackjack

import blackjack.domain.CardMark
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러는 패를 뽑을 수 있다." {
        val dealer = Dealer()

        dealer.addCard(createBasicCard(CardNumber.TEN, CardMark.CLOVER))

        dealer.hand.cards.size() shouldBe 1
    }

    "딜러의 패가 16점 이하일 경우 한장의 카드를 더 뽑을 수 있다." {
        val dealer = Dealer()

        dealer.addCard(createBasicCard(CardNumber.TEN, CardMark.CLOVER))
        dealer.addCard(createBasicCard(CardNumber.SIX, CardMark.CLOVER))

        dealer.shouldHit() shouldBe true
    }

    "딜러의 패가 17점 이상일 경우 더 이상 카드를 받을 수 없다." {
        val dealer = Dealer()

        dealer.addCard(createBasicCard(CardNumber.TEN, CardMark.CLOVER))
        dealer.addCard(createBasicCard(CardNumber.SEVEN, CardMark.CLOVER))

        dealer.shouldHit() shouldBe false
    }
})

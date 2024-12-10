package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DealerTest : FunSpec({
    test("pickCard() 테스트 - 딜러에게 카드를 추가로 줄 수 있다.") {
        // given
        val dealer = Dealer()
        val card = Card(CardNumber.TWO, CardType.SPADE)

        // when
        val pickedDealer = dealer.pickCard(card)

        // then
        pickedDealer.cards.all.size shouldBe 1
    }

    context("canDraw() 테스트") {
        test("카드의 총합이 17 미만이면 true를 반환한다.") {
            // given
            val dealer = Dealer()
            val card = Card(CardNumber.TWO, CardType.SPADE)

            // when
            val pickedDealer = dealer.pickCard(card)

            // then
            pickedDealer.canDraw() shouldBe true
        }

        test("카드의 총합이 17 이상이면 false를 반환한다.") {
            // given
            val dealer = Dealer()
            val card1 = Card(CardNumber.TEN, CardType.SPADE)
            val card2 = Card(CardNumber.TEN, CardType.HEART)
            val card3 = Card(CardNumber.TEN, CardType.CLOVER)

            // when
            val pickedDealer =
                dealer.pickCard(card1)
                    .pickCard(card2)
                    .pickCard(card3)

            // then
            pickedDealer.canDraw() shouldBe false
        }
    }
})

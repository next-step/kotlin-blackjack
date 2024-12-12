package blackjack.step2.domain

import blackjack.step2.view.ConsoleGameInteractor
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
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

    context("playTurn() 테스트") {
        test("딜러가 갖고 있는 카드의 합이 17이상이면 더 이상 카드를 뽑을 수 없다.") {
            // given
            val cards =
                Cards.of(
                    listOf(
                        Card(CardNumber.TEN, CardType.SPADE),
                        Card(CardNumber.SEVEN, CardType.HEART),
                    ),
                )
            val dealer = Dealer(cards = cards)
            val cardPicker = RandomCardPicker()
            val interactor = ConsoleGameInteractor()

            // when
            val playedDealer = dealer.playTurn(cardPicker, interactor)

            // then
            playedDealer.cards.all.size shouldBe 2
        }

        test("딜러가 갖고 있는 카드의 합이 17미만이면 카드를 더 뽑을 수 있다.") {
            // given
            val cards =
                Cards.of(
                    listOf(
                        Card(CardNumber.TEN, CardType.SPADE),
                        Card(CardNumber.SIX, CardType.HEART),
                    ),
                )
            val dealer = Dealer(cards = cards)
            val cardPicker = RandomCardPicker()
            val interactor = ConsoleGameInteractor()

            // when
            val playedDealer = dealer.playTurn(cardPicker, interactor)

            // then
            playedDealer.cards.all.size shouldBeGreaterThan 2
        }
    }
})

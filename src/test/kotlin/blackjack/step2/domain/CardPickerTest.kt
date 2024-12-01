package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardPickerTest : FunSpec({
    context("CardPicker의 pick 함수 테스트") {
        test("pick() 함수를 호출하면 Card를 반환한다") {
            // given
            val givenCard = Card(number = CardNumber.ACE, type = CardType.CLOVER)
            val cardPicker =
                object : CardPicker {
                    override fun pick(): Card {
                        return givenCard
                    }

                    override fun pick(count: Int): List<Card> {
                        return listOf()
                    }
                }

            // when
            val pickedCard = cardPicker.pick()

            // then
            pickedCard shouldBe givenCard
        }
    }

    context("CardPicker의 pick(count: Int) 함수 테스트") {
        test("pick 함수를 호출하면 Card 리스트를 반환한다") {
            // given
            val givenCard1 = Card(number = CardNumber.ACE, type = CardType.CLOVER)
            val givenCard2 = Card(number = CardNumber.TWO, type = CardType.CLOVER)
            val givenCard3 = Card(number = CardNumber.THREE, type = CardType.CLOVER)
            val cardPicker =
                object : CardPicker {
                    override fun pick(): Card {
                        return givenCard1
                    }

                    override fun pick(count: Int): List<Card> {
                        return listOf(givenCard1, givenCard2, givenCard3)
                    }
                }

            // when
            val pickedCard = cardPicker.pick(count = 3)

            // then
            pickedCard shouldBe listOf(givenCard1, givenCard2, givenCard3)
        }
    }
})

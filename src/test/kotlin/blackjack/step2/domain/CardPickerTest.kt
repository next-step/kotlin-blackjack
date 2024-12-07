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
                }

            // when
            val pickedCard = cardPicker.pick()

            // then
            pickedCard shouldBe givenCard
        }
    }
})

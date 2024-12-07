package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain

class RandomCardPickerTest : FunSpec({
    context("RandomCardPicker 클래스 테스트") {
        test("단일 카드를 랜덤하게 선택한다.") {
            // Given
            val cardPicker = RandomCardPicker()

            // When
            val card = cardPicker.pick()

            // Then
            CardNumber.entries shouldContain card.number
            CardType.entries shouldContain card.type
        }
    }
})

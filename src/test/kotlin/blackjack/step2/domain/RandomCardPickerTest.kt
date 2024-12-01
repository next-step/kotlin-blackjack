package blackjack.step2.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeUnique
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldNotBe

class RandomCardPickerTest : FunSpec({
    context("RandomCardPicker 클래스 테스트") {
        test("단일 카드를 랜덤하게 선택한다.") {
            // Given
            val cardPicker = RandomCardPicker()

            // When
            val card = cardPicker.pick()

            // Then
            card.number shouldNotBe null
            card.type shouldNotBe null
        }

        test("여러 개의 카드를 랜덤하게 선택한다.") {
            // Given
            val cardPicker = RandomCardPicker()
            val count = 5

            // When
            val cards = cardPicker.pick(count)

            // Then
            cards.size shouldBeGreaterThanOrEqual 1 // 최소 1개의 카드가 반환되어야 함
            cards.shouldBeUnique() // 중복 없는지 확인
        }

        test("선택한 카드가 항상 고유한지 확인한다.") {
            // Given
            val cardPicker = RandomCardPicker()
            val count = 52

            // When
            val cards = cardPicker.pick(count.coerceAtMost(52)) // 최대 카드 수 조정

            // Then
            cards.shouldBeUnique()
        }

        test("잘못된 카운트를 입력하면 IllegalArgumentException이 발생한다") {
            // Given
            val cardPicker = RandomCardPicker()
            val invalidCount = -1 // 유효하지 않은 카드 수

            // When & Then
            shouldThrow<IllegalArgumentException> {
                cardPicker.pick(invalidCount)
            }
        }

        test("카드 갯수를 초과해서 요청하면 예외가 발생한다.") {
            // Given
            val cardPicker = RandomCardPicker()
            val count = 100 // 카드 수를 초과한 값

            // When & Then
            shouldThrow<IllegalArgumentException> {
                cardPicker.pick(count)
            }
        }
    }
})

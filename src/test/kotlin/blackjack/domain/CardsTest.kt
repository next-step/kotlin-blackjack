package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class CardsTest : FunSpec({
    context("객체 생성") {
        test("다수의 카드를 가진 객체를 생성한다.") {
            val cloverAce = Card(CardPattern.CLOVER, CardValue.ACE)
            val diamondAce = Card(CardPattern.DIAMOND, CardValue.ACE)
            shouldNotThrowAny {
                Cards(linkedSetOf(cloverAce, diamondAce))
            }
        }
    }
    context("sum()") {
        test("카드의 값을 더한 결과를 반환한다.") {
            table(
                headers("cards", "expectedResult"),
                row(Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.JACK), Card(CardPattern.DIAMOND, CardValue.QUEEN))), 20),
                row(Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.QUEEN), Card(CardPattern.DIAMOND, CardValue.KING))), 20),
                row(Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.KING), Card(CardPattern.DIAMOND, CardValue.TEN))), 20),
                row(Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.TWO), Card(CardPattern.DIAMOND, CardValue.TEN))), 12),
            ).forAll { cards, expectedResult ->
                cards.sum() shouldBe expectedResult
            }
        }
        test("ACE 제외 카드의 합이 10 이하일 경우 ACE = 11, 합이 10 초과일 경우 ACE = 1로 계산하여 카드의 값을 더한 결과를 반환한다.") {
            table(
                headers("cards", "expectedResult"),
                row(Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.JACK), Card(CardPattern.DIAMOND, CardValue.ACE))), 21),
                row(Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.TEN), Card(CardPattern.DIAMOND, CardValue.ACE))), 21),
                row(Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.QUEEN), Card(CardPattern.DIAMOND, CardValue.KING), Card(CardPattern.DIAMOND, CardValue.ACE))), 21),
                row(Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.EIGHT), Card(CardPattern.DIAMOND, CardValue.TWO), Card(CardPattern.DIAMOND, CardValue.ACE))), 21),
                row(Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.EIGHT), Card(CardPattern.CLOVER, CardValue.ACE), Card(CardPattern.DIAMOND, CardValue.ACE))), 20),
            ).forAll { cards, expectedResult ->
                cards.sum() shouldBe expectedResult
            }
        }
    }
    context("add()") {
        test("입력받은 카드를 추가한다.") {
            val cloverAce = Card(CardPattern.CLOVER, CardValue.ACE)
            val diamondAce = Card(CardPattern.DIAMOND, CardValue.ACE)
            val cards = Cards(linkedSetOf(cloverAce, diamondAce))

            cards.add(Card(CardPattern.HEART, CardValue.ACE))

            cards.cards.size shouldBe 3
        }
        test("입력받은 카드가 중복된 카드일 경우 예외가 발생한다.") {
            val cloverAce = Card(CardPattern.CLOVER, CardValue.ACE)
            val diamondAce = Card(CardPattern.DIAMOND, CardValue.ACE)
            val cards = Cards(linkedSetOf(cloverAce, diamondAce))

            shouldThrow<IllegalArgumentException> {
                cards.add(cloverAce)
            }
        }
    }
})

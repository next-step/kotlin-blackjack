package blackjack

import blackjack.Denomination.ACE
import blackjack.Denomination.TWO
import blackjack.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({

    context("init") {
        test("중복된 카드가 저장될 경우 예외가 발생한다.") {
            val givenCards = listOf(Card(SPADE, ACE), Card(SPADE, ACE))
            val exception = shouldThrowExactly<IllegalArgumentException> { Cards(givenCards) }
            exception.message shouldBe "중복된 카드가 저장될 수 없다."
        }

        test("카드 리스트를 생성한다.") {
            val actual = Cards(listOf(Card(SPADE, ACE), Card(SPADE, TWO)))
            actual.values shouldHaveSize 2
        }
    }

    context("addCard") {
        test("중복된 카드를 추가하는 경우 예외가 발생한다.") {
            val cards = Cards(listOf(Card(SPADE, ACE), Card(SPADE, TWO)))
            val exception = shouldThrowExactly<IllegalArgumentException> { cards.addCard(Card(SPADE, ACE)) }
            exception.message shouldBe "이미 존재하는 카드를 추가할 수 없다."
        }
    }
})

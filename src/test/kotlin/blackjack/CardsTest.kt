package blackjack

import blackjack.Denomination.ACE
import blackjack.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({

    context("init") {
        test("중복된 카드가 저장될 경우 예외가 발생한다.") {
            val givenCards = listOf(Card(SPADE, ACE), Card(SPADE, ACE))
            val exception = shouldThrowExactly<IllegalArgumentException> { Cards(givenCards) }
            exception.message shouldBe "중복된 카드가 저장될 수 없다."
        }
    }
})

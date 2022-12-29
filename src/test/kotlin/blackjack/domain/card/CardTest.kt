package blackjack.domain.card

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * @see Card
 */
class CardTest : FunSpec({

    context("Card") {
        val anyCardShape = mockk<CardShape>()

        test("fun mainScore(): 카드의 메인 값을 가져온다.") {
            val testCard = Card(CardNumber.ACE, anyCardShape)

            testCard.mainScore() shouldBe 11
        }

        test("fun secondaryScore(): 카드의 두번째 값을 가져온다.") {
            val testCard = Card(CardNumber.ACE, anyCardShape)

            testCard.secondaryScore() shouldBe 1
        }
    }
})

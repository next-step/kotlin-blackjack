package blackjack.domain.card

import blackjack.domain.ScoreOverFlowException
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * @see Cards
 */
class CardsTest : FunSpec({

    context("fun isFull():") {
        val anyShape = mockk<CardShape>()
        val cardOfAce = Card(CardNumber.ACE, anyShape)
        val cardOfTen = Card(CardNumber.TEN, anyShape)

        test("21 미만이면 false를 반환한다.") {
            val cards = Cards()

            cards.add(cardOfTen)
            cards.add(cardOfTen)

            cards.getScore() shouldBe 20
            cards.isFull() shouldBe false
        }

        test("21 이상이면 true를 반환한다.") {
            val cards = Cards()

            cards.add(cardOfAce)
            cards.add(cardOfTen)

            cards.getScore() shouldBe 21
            cards.isFull() shouldBe true
            shouldThrowExactly<ScoreOverFlowException> {
                cards.add(cardOfAce)
            }
        }
    }

    context("fun isBlackJack():") {
        val anyCardShape = mockk<CardShape>()
        val cardOfAce = Card(CardNumber.ACE, anyCardShape)
        val cardOfTen = Card(CardNumber.TEN, anyCardShape)

        test("블랙잭인 경우 true를 반환한다.") {
            val blackJackCards = Cards()

            blackJackCards.add(cardOfAce)
            blackJackCards.add(cardOfTen)

            blackJackCards.isBlackJack() shouldBe true
        }

        test("블랙잭이 아닌 경우 false 반환한다.") {
            val nonBlackJackCards = Cards()

            nonBlackJackCards.add(cardOfTen)
            nonBlackJackCards.add(cardOfTen)

            nonBlackJackCards.isBlackJack() shouldBe false
        }
    }
})

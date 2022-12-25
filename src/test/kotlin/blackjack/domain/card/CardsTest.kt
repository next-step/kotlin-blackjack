package blackjack.domain.card

import blackjack.domain.ScoreOverFlowException
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * @see Cards
 */
class CardsTest : ExpectSpec({

    context("Cards") {
        val anyShape = mockk<CardShape>()
        val cardOfAce = Card(CardNumber.ACE, anyShape)
        val cardOfTen = Card(CardNumber.TEN, anyShape)

        expect("카드를 추가 했을 때 합이 21 넘지 않으면 더 받을 수 있다.") {
            val cards = Cards()

            cards.add(cardOfTen)

            cards.getScore() shouldBe 10
            cards.isFull() shouldBe false
        }

        expect("카드를 추가 했을 때 합이 21이상이 되면 더 받을 수 없다.") {
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
})

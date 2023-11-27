package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Hand
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContain

data class HandScoreTestData(val cardList: List<Card>, val expectedScore: List<Int>)
class HandTest : FunSpec({
    context("ACE는 1점 혹은 11점으로 계산되고, JQK는 10점으로 계산된다. 그 외는 자신의 숫자대로 계산된다.") {
        withData(
            listOf(
                HandScoreTestData(
                    cardList = listOf(
                        Card(number = CardNumber.JACK, shape = CardShape.DIAMOND)
                    ),
                    expectedScore = listOf(10)
                ),
                HandScoreTestData(
                    cardList = listOf(
                        Card(number = CardNumber.ACE, shape = CardShape.DIAMOND),
                        Card(number = CardNumber.ACE, shape = CardShape.HEART),
                        Card(number = CardNumber.ACE, shape = CardShape.SPADE),
                    ),
                    expectedScore = listOf(3, 13, 23, 33)
                ),
                HandScoreTestData(
                    cardList = listOf(
                        Card(number = CardNumber.ACE, shape = CardShape.DIAMOND),
                        Card(number = CardNumber.KING, shape = CardShape.HEART),
                    ),
                    expectedScore = listOf(11, 21)
                ),
            )
        ) { (cardList, expectedScore) ->
            val hand = Hand(cardList = cardList)

            hand.getPossibleScore().forAll {
                expectedScore shouldContain it
            }
        }
    }
})

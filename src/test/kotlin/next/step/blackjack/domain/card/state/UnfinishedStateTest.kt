package next.step.blackjack.domain.card.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.card.Cards

class UnfinishedStateTest : DescribeSpec({

    describe("UnfinishedState") {
        context("cards 조건에 따라 다른 다음 상태 제공") {
            data class NextStateExpected(val cards: Cards, val nextState: CardsState)

            withData(
                NextStateExpected(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.ACE, CardSymbol.HEART)
                        )
                    ),
                    BlackjackState
                ),
                NextStateExpected(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.ACE, CardSymbol.HEART)
                        )
                    ),
                    FinishedState
                ),
                NextStateExpected(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.TWO, CardSymbol.HEART)
                        )
                    ),
                    BurstState
                ),
                NextStateExpected(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.KING, CardSymbol.HEART)
                        )
                    ),
                    UnfinishedState
                )
            ) { (cards, expected) ->
                UnfinishedState.next(cards) shouldBe expected
            }
        }
    }
})

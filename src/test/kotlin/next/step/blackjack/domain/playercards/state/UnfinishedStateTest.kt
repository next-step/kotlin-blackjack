package next.step.blackjack.domain.playercards.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

class UnfinishedStateTest : DescribeSpec({

    describe("UnfinishedState") {
        context("cards 조건에 따라 다른 다음 상태 제공") {
            data class NextStateExpected(val cards: Cards, val nextState: PlayerCardsState)

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
        context("카드 상태에 따라 게임 결과가 달라짐") {
            data class ResultExpected(val state: PlayerCardsState, val result: GameResult)
            withData(
                ResultExpected(BlackjackState, GameResult.LOSE),
                ResultExpected(UnfinishedState, GameResult.UNDECIDED),
                ResultExpected(FinishedState, GameResult.LOSE),
                ResultExpected(BurstState, GameResult.WIN),
            ) { (state, result) ->
                UnfinishedState.fight(state) shouldBe result
            }
        }
    }
})

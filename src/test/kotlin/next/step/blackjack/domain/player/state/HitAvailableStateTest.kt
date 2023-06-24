package next.step.blackjack.domain.player.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.player.PlayerCards

class HitAvailableStateTest : DescribeSpec({

    describe("HitAvailableState") {
        context("canHit") {
            it("항상 true 제공") {
                HitAvailableState.canHit() shouldBe true
            }
        }
        context("cards 조건에 따라 다른 다음 상태 제공") {
            data class NextStateExpected(val cards: PlayerCards, val nextState: PlayerState)

            withData(
                NextStateExpected(
                    PlayerCards.of(
                        listOf(
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.ACE, CardSymbol.HEART)
                        )
                    ), BlackjackState
                ),
                NextStateExpected(
                    PlayerCards.of(
                        listOf(
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.ACE, CardSymbol.HEART)
                        )
                    ), FinishedState
                ),
                NextStateExpected(
                    PlayerCards.of(
                        listOf(
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.TWO, CardSymbol.HEART)
                        )
                    ), BurstState
                ),
                NextStateExpected(
                    PlayerCards.of(
                        listOf(
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.KING, CardSymbol.HEART)
                        )
                    ), HitAvailableState
                )
            ) { (cards, expected) ->
                HitAvailableState.next(cards) shouldBe expected
            }
        }

    }
})

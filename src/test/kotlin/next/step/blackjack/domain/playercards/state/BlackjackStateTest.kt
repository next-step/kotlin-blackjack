package next.step.blackjack.domain.playercards.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult
import org.junit.jupiter.api.assertThrows

class BlackjackStateTest : DescribeSpec({

    describe("BlackjackState") {
        context("next") {
            it("항상 예외 발생") {
                assertThrows<IllegalArgumentException> { BlackjackState.next(Cards.of(emptyList())) }
            }
        }
        context("카드 상태에 따라 게임 결과가 달라짐") {
            data class ResultExpected(val state: PlayerCardsState, val result: GameResult)
            withData(
                ResultExpected(BlackjackState, GameResult.TIE),
                ResultExpected(StayState, GameResult.WIN),
                ResultExpected(HitState, GameResult.WIN),
                ResultExpected(BurstState, GameResult.WIN),
            ) { (state, result) ->
                BlackjackState.fight(state) shouldBe result
            }
        }
    }
})

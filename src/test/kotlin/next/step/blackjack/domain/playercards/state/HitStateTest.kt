package next.step.blackjack.domain.playercards.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult
import org.junit.jupiter.api.assertThrows

class HitStateTest : DescribeSpec({

    describe("HitState") {
        context("next") {
            it("항상 예외 발생") {
                assertThrows<IllegalArgumentException> { HitState.next(Cards.of(emptyList())) }
            }
        }
        context("카드 상태에 따라 게임 결과가 달라짐") {
            data class ResultExpected(val state: PlayerCardsState, val result: GameResult)
            withData(
                ResultExpected(BlackjackState, GameResult.LOSE),
                ResultExpected(StayState, GameResult.WIN),
                ResultExpected(HitState, GameResult.TIE),
                ResultExpected(BurstState, GameResult.WIN),
            ) { (state, result) ->
                HitState.fight(state) shouldBe result
            }
        }
    }
})

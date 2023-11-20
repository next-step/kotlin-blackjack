package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.mock.InputProcessorMock
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.types.shouldBeTypeOf

class DetermineWinnerStageTest : DescribeSpec({
    describe("다음 스테이지 반환") {
        context("게임 완료 후 다음 스테이지 요청") {
            val game = BlackJackGame(InputProcessorMock())

            val stage = DetermineWinnerStage()

            it("게임 종료 스테이지 반환") {
                val nextStage = stage.nextStage(game)

                nextStage.shouldBeTypeOf<EndStage>()
            }
        }
    }
})

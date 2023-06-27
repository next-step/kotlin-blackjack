package next.step.blackjack.domain.game

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameOddsTest : DescribeSpec({

    describe("GameOdds") {
        context("from으로 생성") {
            it("블랙잭으로 이기면 BLACKJACK") {
                GameOdds.from(GameResult.WIN, true) shouldBe GameOdds.BLACKJACK
            }
            it("이겼는데 블랙잭은 아니면 WIN") {
                GameOdds.from(GameResult.WIN, false) shouldBe GameOdds.WIN
            }
            it("졌으면 LOSE") {
                GameOdds.from(GameResult.LOSE, false) shouldBe GameOdds.LOSE
            }
            it("비기면 TIE") {
                GameOdds.from(GameResult.TIE, false) shouldBe GameOdds.TIE
            }
        }
    }

})

package next.step.blackjack.domain.game

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerGameResultsTest : DescribeSpec({
    describe("DealerGameResults") {
        context("zeros로 생성하면") {
            it("GameResult 키에 대해 value가 0인 Map으로 생성") {
                DealerGameResults.zeros() shouldBe DealerGameResults.of(
                    mutableMapOf(
                        GameResult.WIN to 0,
                        GameResult.LOSE to 0,
                        GameResult.TIE to 0,
                        GameResult.UNDECIDED to 0
                    )
                )
            }
        }

        context("add하면") {
            it("GameResult 키에 대해 value가 증가함") {
                val dealerGameResults = DealerGameResults.zeros()

                dealerGameResults.add(GameResult.WIN)
                dealerGameResults.add(GameResult.WIN)
                dealerGameResults.add(GameResult.LOSE)

                dealerGameResults shouldBe DealerGameResults.of(
                    mutableMapOf(
                        GameResult.WIN to 2,
                        GameResult.LOSE to 1,
                        GameResult.TIE to 0,
                        GameResult.UNDECIDED to 0
                    )
                )
            }
        }

        context("results하면") {
            it("불변 Map을 UNDECIDED 키 제외하고 제공") {
                val dealerGameResults = DealerGameResults.zeros()

                dealerGameResults.add(GameResult.WIN)
                dealerGameResults.add(GameResult.WIN)
                dealerGameResults.add(GameResult.LOSE)

                dealerGameResults.results() shouldBe
                        mapOf(
                            GameResult.WIN to 2,
                            GameResult.LOSE to 1,
                            GameResult.TIE to 0
                        )
            }
        }
    }

})

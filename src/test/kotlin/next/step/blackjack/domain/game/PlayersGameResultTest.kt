package next.step.blackjack.domain.game

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayersGameResultTest : DescribeSpec({

    describe("PlayerGameResult") {
        context("결과를 추가하면") {
            it("Map에 플레이어이름을 key로 가지고, 게임결과를 value로 가지는 데이터를 추가") {
                val result = PlayersGameResult.empty()

                result.put("player", GameResult.WIN)

                result shouldBe PlayersGameResult.of(mutableMapOf("player" to GameResult.WIN))
            }
        }
        context("results 호출하면") {
            it("불변 Map 객체로 제공") {
                val result = PlayersGameResult.empty()

                result.put("player", GameResult.WIN)

                result.results() shouldBe mapOf("player" to GameResult.WIN)
            }
        }
    }
})

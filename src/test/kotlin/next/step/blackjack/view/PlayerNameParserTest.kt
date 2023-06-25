package next.step.blackjack.view

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.player.PlayerName
import next.step.blackjack.domain.player.PlayerNames

class PlayerNameParserTest : DescribeSpec({

    describe("PlayerNameParser method") {
        context("parse로 string을 넣으면") {
            it("콤마 기준으로 분리해서 PlayerNames 생성") {
                PlayerNameParser.parse("test, test2") shouldBe PlayerNames.of(
                    setOf(
                        PlayerName.of("test"),
                        PlayerName.of("test2")
                    )
                )
            }
        }
    }
})

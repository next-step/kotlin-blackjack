package blackjack.view

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardShape
import blackjack.player.Player
import blackjack.player.Players
import blackjack.view.output.StubOutput
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class OutputViewTest : DescribeSpec({
    describe("start") {
        it("첫 메세지는 전체 Player의 이름이 출력된다") {
            val output = StubOutput()
            val sut = OutputView(output)

            sut.start(
                Players(
                    listOf(
                        Player("pobi"),
                        Player("jason")
                    )
                )
            )

            output.message shouldBe "pobi, jason에게 2장을 나누었습니다."
        }
    }

    describe("showAllPlayersCard") {
        it("player들의 이름과 전체 카드들이 출력된다") {
            val output = StubOutput()
            val sut = OutputView(output)

            sut.showAllPlayersCard(
                Players(
                    listOf(
                        Player.of(
                            "pobi",
                            listOf(
                                Card(CardNumber.TWO, CardShape.HEART),
                                Card(CardNumber.EIGHT, CardShape.SPADE)
                            )
                        ),
                        Player.of(
                            "jason",
                            listOf(
                                Card(CardNumber.SEVEN, CardShape.CLOVER),
                                Card(CardNumber.KING, CardShape.SPADE)
                            )
                        ),
                    )
                )
            )

            val expect = "pobi카드: 2하트, 8스페이드\n" +
                "jason카드: 7클로버, K스페이드"

            output.message shouldBe expect
        }
    }

    describe("showPlayerCard") {
        it("player의 이름과 전체 카드들이 출력된다") {
            val output = StubOutput()
            val sut = OutputView(output)

            sut.showPlayerCard(
                Player.of(
                    "pobi",
                    listOf(
                        Card(CardNumber.TWO, CardShape.HEART),
                        Card(CardNumber.EIGHT, CardShape.SPADE)
                    )
                )
            )

            output.message shouldBe "pobi카드: 2하트, 8스페이드"
        }
    }

    describe("Extension") {
        context("Players.names()를 호출하면") {
            it("player들의 이름이 ,로 합쳐진다") {
                val sut = Players(
                    listOf(
                        Player("pobi"),
                        Player("jason"),
                    )
                )

                val result = sut.names()

                result shouldBe "pobi, jason"
            }
        }
    }
})

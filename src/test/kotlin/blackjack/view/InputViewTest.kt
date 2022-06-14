package blackjack.view

import blackjack.view.input.StubInput
import blackjack.view.output.StubOutput
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class InputViewTest : DescribeSpec({

    describe("askPlayers") {
        context("players 입력일 경우") {
            it("시작 메세지가 존재한다") {
                val output = StubOutput()
                val sut = InputView(StubInput.ofPlayers(""), output)

                sut.players

                output.message shouldBe "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
            }

            it(", 만큼 이름이 분리된다") {
                val sut = InputView(StubInput.ofPlayers("pobi,jason"), StubOutput())

                sut.players.size shouldBe 2
                sut.players[0] shouldBe "pobi"
                sut.players[1] shouldBe "jason"
            }

            it("띄어쓰기는 무시하고 이름이 분리된다") {
                val sut = InputView(StubInput.ofPlayers(" pobi, jason "), StubOutput())

                sut.players[0] shouldBe "pobi"
                sut.players[1] shouldBe "jason"
            }

            it(", 가 없는 입력은 1명으로 반환된다") {
                val sut = InputView(StubInput.ofPlayers("pobi"), StubOutput())

                sut.players.size shouldBe 1
                sut.players[0] shouldBe "pobi"
            }
        }

        context("hasNextCard 입력일 경우") {
            it("카드 추가 발급 메세지가 존재한다") {
                val output = StubOutput()
                val sut = InputView(StubInput.ofNextCard("y"), output)

                sut.hasNextCard("pobi")

                output.message shouldBe "pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
            }

            it("y를 입력하면 true가 반환된다") {
                val sut = InputView(StubInput.ofNextCard("y"), StubOutput())

                val result = sut.hasNextCard("pobi")

                result shouldBe true
            }

            it("n를 입력하면 false가 반환된다") {
                val sut = InputView(StubInput.ofNextCard("n"), StubOutput())

                val result = sut.hasNextCard("pobi")

                result shouldBe false
            }

            it("y 혹은 n이 아니면 IllegalArgumentException 가 발생한다") {
                val sut = InputView(StubInput.ofNextCard("a"), StubOutput())

                val exception = shouldThrow<IllegalArgumentException> {
                    sut.hasNextCard("")
                }

                exception.message shouldBe "y 혹은 n으로 입력해주세요. 현재입력값=a"
            }
        }
    }
})

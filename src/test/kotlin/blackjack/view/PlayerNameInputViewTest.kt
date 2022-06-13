package blackjack.view

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class PlayerNameInputViewTest : FreeSpec({

    "참가자 이름입력 안내문구를 출력한다" {
        val io = StubIO()
        val view = PlayerNameInputView(io)
        io.addInput("user")

        view.inputPlayerNames()

        io.printed shouldBe listOf("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)", "")
    }

    "잘못 입력하는 경우 다시 입력받게 한다" - {
        listOf(
            "공백 입력" to "",
            "쉼표만 입력" to ",,,",
        ).forEach { (message, input) ->
            message {
                val io = StubIO()
                val view = PlayerNameInputView(io)
                io.addInput(input)
                io.addInput("user")

                view.inputPlayerNames()

                io.printed shouldHaveSize 3
                io.printed[1] shouldBe "잘못 입력하셨습니다. 다시 입력하세요."
            }
        }
    }

    "참가자 이름을 반환한다" {
        val io = StubIO()
        val view = PlayerNameInputView(io)
        io.addInput("user1,user2")

        val names = view.inputPlayerNames()

        names shouldBe listOf("user1", "user2")
    }
})

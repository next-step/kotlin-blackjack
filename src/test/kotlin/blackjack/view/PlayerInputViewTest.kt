package blackjack.view

import blackjack.domain.Bet
import blackjack.domain.dto.GameParticipationRequest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class PlayerInputViewTest : FreeSpec({

    "참가자 이름입력 안내문구를 출력한다" {
        val io = StubIO()
        val view = PlayerInputView(io)
        io.addInput("user")
        io.addInput("10000")

        view.inputPlayerInfo()

        io.printed shouldBe listOf(
            "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)",
            "",
            "user의 배팅 금액은?",
            "",
        )
    }

    "잘못 입력하는 경우 다시 입력받게 한다" - {
        listOf(
            "공백 입력" to "",
            "쉼표만 입력" to ",,,",
        ).forEach { (message, input) ->
            message {
                val io = StubIO()
                val view = PlayerInputView(io)
                io.addInput(input)
                io.addInput("user")
                io.addInput("10")

                view.inputPlayerInfo()

                io.printed shouldHaveSize 5
                io.printed[1] shouldBe "잘못 입력하셨습니다. 다시 입력하세요."
            }
        }
    }

    "게임참가 요청정보를 반환한다" {
        val io = StubIO()
        val view = PlayerInputView(io)
        io.addInput("user1,user2")
        io.addInput("10000")
        io.addInput("50000")

        val request = view.inputPlayerInfo()

        request shouldBe listOf(
            GameParticipationRequest("user1", Bet(10000)),
            GameParticipationRequest("user2", Bet(50000)),
        )
    }
})

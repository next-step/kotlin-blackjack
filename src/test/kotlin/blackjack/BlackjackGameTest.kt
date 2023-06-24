package blackjack

import blackjack.domain.BlackjackGame
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : StringSpec({

    "게임에 참여할 사람의 숫자는 쉼표 기준으로 분리한 리스트의 size" {
        val actualInput = ("Park, Kim, Lee")
        val game = BlackjackGame(actualInput)
        game.getPlayers().size shouldBe 3
    }
})

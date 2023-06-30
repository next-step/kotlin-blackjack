package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.domain.RandomCardStrategy
import blackjack.view.InputView
import blackjack.view.OutputView
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : StringSpec({

    "게임에 참여할 사람의 숫자는 쉼표 기준으로 분리한 리스트의 size" {
        val actualInput = listOf("Park", "Kim", "Lee")
        val game = BlackjackGame(InputView(), OutputView(), Deck(RandomCardStrategy()))
        game.transformToPlayers(actualInput)
        game.getPlayers().size shouldBe 3
    }
})

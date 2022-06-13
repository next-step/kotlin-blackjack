package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({
    "랜덤한 카드 2장을 받아서 게임을 시작한다." {
        val randomCardPlayer = Player("test")
        randomCardPlayer.start()
        randomCardPlayer.deck.size() shouldBe 2
    }
})

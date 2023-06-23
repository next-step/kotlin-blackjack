package blackjack.controller

import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

@DisplayName("블랙잭 게임")
class BlackjackGameTest : StringSpec({

    "블랙잭 게임" {
        BlackjackGame(
            { false },
            { listOf("test1", "test2") },
            { player -> player.deck.cards.size shouldBe 2 },
            { _, count -> count shouldBe 2 },
            { players -> players.players.map { it.deck.cards.size }.shouldContainExactly(2, 2) }
        ).start()
    }
})

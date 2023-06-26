package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어는 이름을 가진다" {
        val player = Player("Park", mutableListOf())
        player.name shouldBe "Park"
    }

    "플레이어는 게임 시작 시 2장의 카드를 지급 받는다" {
        val game = BlackjackGame(listOf("Park", "Lee"))
        val players = game.getPlayers()
        players.forEach { player ->
            player.cards.size shouldBe 2
        }
    }
})

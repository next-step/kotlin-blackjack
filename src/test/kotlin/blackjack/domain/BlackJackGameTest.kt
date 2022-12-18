package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class BlackJackGameTest : StringSpec() {
    private lateinit var game: BlackJackGame

    init {
        beforeEach {
            game = BlackJackGame()
        }

        "2명의 참가자가 게임을 시작할 때 카드를 2장씩 지급한다." {
            val names = listOf("플레이어2", "플레이어2")
            val players = game.makePlayers(names)

            players.values.forEach {
                it.cards.values.size shouldBe 2
            }
        }
    }
}

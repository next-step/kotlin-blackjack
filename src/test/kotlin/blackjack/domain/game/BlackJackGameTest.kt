package blackjack.domain.game

import blackjack.domain.player.Players
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({
    "게임이 생성되고 플레이어들이 준비하면 각각 카드를 ${START_DRAW_COUNT}장씩 나눠받는다." {
        val players = Players.of(listOf("pavlo", "wade"))
        val blackJackGame = BlackJackGame.create(players)
        blackJackGame.start()

        players.players.forAll {
            it.getHands().size shouldBe START_DRAW_COUNT
        }
    }
})

package nextstep.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class GameTest : StringSpec({

    "게임 라운드가 시작되면 모든 플레이어는 카드 2장을 지급받는다." {
        val game = Game(listOf(Player("jack"), Player("pobi")))

        game.players.forAll { it.cards.size shouldBe  2 }
    }
})

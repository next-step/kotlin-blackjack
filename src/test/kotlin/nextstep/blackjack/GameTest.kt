package nextstep.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class GameTest : StringSpec({

    "게임 라운드가 시작되면 모든 플레이어는 카드 2장을 지급받는다." {
        val game = Game(listOf(Player("jack"), Player("pobi")))

        game.players.forAll { it.cards.size shouldBe 2 }
    }

    "플레이어가 원하면 특정 플레이어에게 카드 1장을 추가로 지급한다." {
        val game = Game(listOf(Player("jack"), Player("pobi")))

        game.drawTo(Player("jack"))

        game.players.first { it.name == "jack" }.cards.size shouldBe 3
    }
})

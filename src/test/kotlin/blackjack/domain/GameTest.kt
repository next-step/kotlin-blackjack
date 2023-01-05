package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameTest : StringSpec({

    "쉼표로 참여자를 구분한다" {
        val game = Game(listOf(Gamer("a"), Gamer("b"), Gamer("c")))
        game.getParticipantNames() shouldBe listOf("딜러", "a", "b", "c")
    }

    "참여자를 확인한다" {
        val dealer = Dealer()
        val gamer1 = Gamer("a")
        val gamer2 = Gamer("b")
        val gamerList = listOf(gamer1, gamer2)
        val game = Game(dealer, gamerList)
        game.getParticipant() shouldBe listOf(dealer, gamer1, gamer2)
    }
})

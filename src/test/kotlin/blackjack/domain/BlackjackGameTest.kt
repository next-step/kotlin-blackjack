package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class BlackjackGameTest : StringSpec({

    "start()는 모든 플레이어에게 두 장의 카드를 지급한다." {
        val blackjackGame = createBlackjackFixture()
        blackjackGame.start()
        val players = blackjackGame.getParticipants().players

        players.forAll {
            it.cards.size shouldBe 2
        }
    }

    "canDraw()는 플레이어가 카드를 받을 수 있으면 true 를 반환한다." {
        val blackjackGame = createBlackjackFixture()
        blackjackGame.canDraw("a") shouldBe true
    }

    "canDraw()는 플레이어가 카드를 받을 수 없으면 false 를 반환한다." {
        val blackjackGame = createBlackjackFixture()
        repeat(3) { blackjackGame.dealCard("a") }
        blackjackGame.canDraw("a") shouldBe false
    }

    "dealCard()는 플레이어에게 카드를 지급한다." {
        val blackjackGame = createBlackjackFixture()
        blackjackGame.dealCard("a")

        blackjackGame.getParticipant("a").cards.size shouldBe 1
    }
})

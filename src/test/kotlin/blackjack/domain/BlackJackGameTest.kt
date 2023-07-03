package blackjack.domain

import blackjack.test.TestUtils
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({
    "게임이 시작되면 각 플레이어는 2장의 카드를 지급받는다." {
        val deck = Deck.create()
        val game = BlackJackGame(deck)
        val initPlayers = Players.of(List(2) { TestUtils.randomString(size = 2) })

        val players = game.start(initPlayers)

        players[0].hand.cards.size shouldBe 2
        players[1].hand.cards.size shouldBe 2
    }
})

package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({
    "게임에 참여한 유저들의 정보를 가지고 있다." {
        val game = BlackJackGame.createGame(createPlayers(2), Deck())

        game.players.size shouldBe 2
    }

    "게임이 시작되면 각 유저들은 2개의 패를 지급받는다." {
        val game = BlackJackGame.createGame(createPlayers(2), Deck())

        game.players.forEach {
            it.getCards().size shouldBe 2
        }
    }

    "유저가 카드를 뽑기로 결정하면 하나의 카드를 제공한다." {
        val game = BlackJackGame.createGame(createPlayers(2), Deck())
        val player = game.players[0]

        val result = game.drawSingleCardToPlayer(true, game.players[0])

        result shouldBe true
        player.getCards().size shouldBe 3
    }

    "유저가 카드를 뽑지 않기로 결정하면 뽑지 않는다." {
        val game = BlackJackGame.createGame(createPlayers(2), Deck())
        val player = game.players[0]

        val result = game.drawSingleCardToPlayer(false, game.players[0])

        result shouldBe false
        player.getCards().size shouldBe 2
    }
})

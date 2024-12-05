package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({
    "게임에 참여한 유저들의 정보를 가지고 있다." {
        val game = BlackJackGame.createGame(createPlayers(2), RandomDeck())

        game.players.size shouldBe 2
    }

    "게임이 시작되면 각 유저들은 2개의 패를 지급받는다." {
        val game = BlackJackGame.createGame(createPlayers(2), RandomDeck())

        game.players.forEach {
            it.getCards().size shouldBe 2
        }
    }

    "유저들에게 최초 지급된 패가 21일 경우 블랙잭을 선언한다." {
        val game = BlackJackGame.createGame(createPlayers(2), FixedDeck())

        game.hasBlackJackPlayer() shouldBe true
    }

    "유저에게 하나의 카드를 제공한다." {
        val game = BlackJackGame.createGame(createPlayers(2), RandomDeck())
        val player = game.players[0]

        val result = game.drawSingleCardToPlayer(game.players[0])

        result shouldBe true
        player.getCards().size shouldBe 3
    }
})

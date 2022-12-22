package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

internal class BlackJackGameTest : StringSpec({
    lateinit var game: BlackJackGame

    beforeEach {
        game = BlackJackGame()
    }

    "2명의 참가자가 게임을 시작할 때 카드를 2장씩 지급한다." {
        val names = listOf("플레이어2", "플레이어2")
        game.setInitPlayers(names)

        game.players.forEach {
            it.cards.values.size shouldBe 2
        }
    }

    "딜러가 게임을 시작할 때 2장의 카드를 지급한다." {
        game.setInitDealer()
        val initialCardSize = game.dealer.cards.values.size

        initialCardSize shouldBe 2
    }

    "플레이어 수만큼의 플레이어 결과를 반환한다." {
        val players = listOf(
            Player(), Player(), Player()
        )
        val dealer = Dealer()
        val newGame = BlackJackGame(dealer = dealer, players = players)

        newGame.calculateResult()

        newGame.playerResults.size shouldBe 3
    }

    "게임도중 버스트된 플레이어가 있다면 패한 결과를 저장한다." {
        val player = Player()
        val newGame = BlackJackGame()

        newGame.playerResults shouldBe emptyList()
        newGame.addPlayerResultWhenBust(player)
        newGame.playerResults shouldContain PlayerResult(player, ResultStatus.LOSE)
    }
})

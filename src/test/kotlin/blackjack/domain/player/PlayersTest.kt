package blackjack.domain.player

import blackjack.domain.card.Deck
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

/**
 * @see Players
 */
class PlayersTest : FunSpec({

    context("fun startGame(): 게임 시작시 카드 2장을 준다.") {
        val gameDeck = Deck
        val playerA = Player("A")
        val playerB = Player("B")
        val players = Players(listOf(playerA, playerB))

        test("모믄 플레이어들은 게임 시작시 카드 두 장을 받는다.") {
            players.startGame(gameDeck)

            players.players.forEach { player ->
                player.getCards().size shouldBe 2
            }
        }
    }
})

package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.BlackjackGameResult
import blackjack.domain.player.GamePlayers
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `게임에 참여할 Player와 CardDeck이 필요하다`() {
        val player1 = Player()
        val player2 = Player()
        val gamePlayers = GamePlayers.from(player1, player2)
        val blackjackGame = BlackjackGame(gamePlayers)

        blackjackGame.gamePlayers shouldBe gamePlayers
        blackjackGame.deck.shouldNotBeNull()
    }

    @Test
    fun `최초 카드분배시 Player에게 2장의 카드를 나눠준다`() {
        val player = Player()
        val blackjackGame = BlackjackGame(GamePlayers.from(player))

        blackjackGame.initPlayers()

        player.cards.getValue().size shouldBe 2
    }

    @Test
    fun `최초 카드분배 이후에는 Player에게 카드를 1장씩 나눠준다`() {
        val player = Player()
        val blackjackGame = BlackjackGame(GamePlayers.from(player))

        blackjackGame.dealCard(player)

        player.cards.getValue().size shouldBe 1
    }

    @Test
    fun `모든 플레이어의 차례가 끝나면 게임종료 함수가 호출되어 게임 결과를 반환한다`() {
        val gamePlayers = GamePlayers.from(Player(Name("hue")), Player(Name("jason")))
        val blackjackGame = BlackjackGame(gamePlayers)

        blackjackGame.initPlayers()
        gamePlayers.players.forEach { blackjackGame.dealCard(it) }

        // 게임 종료 함수 호출
        val gameResult = blackjackGame.finishPlayers()

        gameResult.shouldBeInstanceOf<BlackjackGameResult>()
    }
}

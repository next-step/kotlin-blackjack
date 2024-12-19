package blackjack

import blackjack.application.BlackjackService
import blackjack.application.CreateGameCommand
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.PlayerCommand
import blackjack.ui.ResultView.render
import blackjack.ui.ResultView.renderActions
import blackjack.ui.ResultView.renderResults

class BlackjackController(
    private val blackjackService: BlackjackService,
) {
    fun start(deck: Deck) {
        // 이름과 베팅 금액
        val names = InputView.getPlayerNames()
        val bets = InputView.getBets(names)

        // 게임 생성
        val game =
            blackjackService.createGame(
                CreateGameCommand(
                    names.toSet(),
                    bets,
                    deck,
                ),
            )
        game.render()

        // 게임 진행
        runGameLoop(game)
        game.dealer.renderActions()

        // 결과 출력
        game.renderResults()
    }

    private fun runGameLoop(game: Game) {
        // 플레이어들의 턴
        while (!game.arePlayersDone) {
            val player = game.currentPlayer
            val command = InputView.getCommand(player)
            handle(command, game)
            ifHitDisplayPlayer(command, player)
        }

        // 딜러의 턴
        game.dealerTurn()
    }

    private fun handle(
        command: PlayerCommand,
        game: Game,
    ) {
        if (command.isHit) {
            game.playerHits()
            return
        }
        game.playerStands()
    }

    private fun ifHitDisplayPlayer(
        command: PlayerCommand,
        player: Player,
    ) {
        if (command.isHit) {
            player.render()
        }
    }
}

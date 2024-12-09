package blackjack

import blackjack.application.BlackjackService
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.PlayerCommand
import blackjack.ui.ResultView

class BlackjackController(
    private val blackjackService: BlackjackService,
) {
    fun start(deck: Deck) {
        // 블랙젝 게임 초기화
        val names = InputView.getPlayerNames()
        val game = blackjackService.initializeGame(names, deck)

        // 초기 상태 출력
        ResultView.displayState(game)

        // 게임 진행
        runGameLoop(game)

        // 딜러 행동 출력
        ResultView.displayDealerActions(game.dealer)

        // 게임 결과 출력
        ResultView.displayState(game, false)

        // 최종 승패 출력
        ResultView.displayResults(game.gameResult())
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
            ResultView.displayPlayer(player)
        }
    }
}

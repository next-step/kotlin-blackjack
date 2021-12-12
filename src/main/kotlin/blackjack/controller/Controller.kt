package blackjack.controller

import blackjack.domain.GamePlayUsers
import blackjack.domain.entity.Player
import blackjack.domain.setup.GameStartSetting
import blackjack.view.InputView
import blackjack.view.OutputView

object Controller {

    fun gameStart(): GamePlayUsers {

        val players = InputView.setPlayer()

        val gamePlayUsers = GamePlayUsers(GameStartSetting.setGame(players))

        OutputView.printSettingPlayer(gamePlayUsers.playUsers)

        return gamePlayUsers
    }

    fun playing(player: Player) {
        while (InputView.hitsAndStay(player.name) && player.scoreCalculation() < 20) {
            player.hits()
            OutputView.printPlayCard(player)
        }
    }

    fun gameEnd(gamePlayUsers: GamePlayUsers) {
        OutputView.gameEnd(gamePlayUsers.playUsers)
    }
}

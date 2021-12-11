package blackjack.play

import blackjack.entity.Player
import blackjack.setup.GameStartSetting
import blackjack.util.PlayerCardCalculation
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
        while (InputView.hitsAndStay(player.name) && PlayerCardCalculation.calculation(player) < 20) {
            player.hits()
            OutputView.printPlayCard(player)
        }
    }

    fun gameEnd(gamePlayUsers: GamePlayUsers) {
        OutputView.gameEnd(gamePlayUsers.playUsers)
    }
}

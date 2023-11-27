package blackjack.contoller

import blackjack.domain.component.BlackjackGameProxy
import blackjack.domain.component.BlackjackInputValidator
import blackjack.domain.model.Player
import blackjack.domain.model.PlayerInfo
import blackjack.domain.model.PlayerName
import blackjack.domain.model.YesNo
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackResultView

class BlackjackController(
    private val blackJackInputView: BlackjackInputView,
    private val blackjackInputValidator: BlackjackInputValidator,
    private val blackjackResultView: BlackjackResultView,
    private val blackjackGameProxy: BlackjackGameProxy
) {
    fun getPlayerNames(): List<PlayerName> {
        val playerNamesString: String? = blackJackInputView.getPlayerNames()

        return convertPlayerNamesStringToList(playerNamesString)
    }

    fun getHitPossible(playerName: PlayerName): Boolean {
        return blackjackGameProxy.isHitPossible(playerName)
    }

    fun getHit(playerName: PlayerName): YesNo {
        return blackJackInputView
            .getHit(playerName.name)
            .run { convertYesNo(this) }
    }

    fun initGame(playerNames: List<PlayerName>) {
        blackjackGameProxy.init(playerNames)
    }

    fun hit(playerName: PlayerName): Player {
        return blackjackGameProxy.hit(playerName)
    }

    fun printInitialGameStatus() {
        blackjackGameProxy
            .fetchPlayerInfos()
            .run { blackjackResultView.printGameInitialStatus(this) }
    }

    fun printGameResultStatus() {
        blackjackGameProxy
            .fetchPlayerInfos()
            .run { blackjackResultView.printGameResultStatus(this) }
    }

    fun printPlayerInfos() {
        blackjackGameProxy
            .fetchPlayerInfos()
            .run { blackjackResultView.printPlayerInfos(this) }
    }

    fun printPlayerInfo(player: Player) {
        val playerInfo = PlayerInfo.from(player)

        blackjackResultView.printPlayerInfo(playerInfo, false)
    }

    private fun convertPlayerNamesStringToList(playerNames: String?): List<PlayerName> {
        return playerNames
            .run { blackjackInputValidator.validatePlayerNamesString(this) }
            .split(PLAYER_NAME_SEPARATOR)
            .run { blackjackInputValidator.validatePlayerNamesSize(this) }
            .map { PlayerName(it) }
    }

    private fun convertYesNo(yesNo: String?): YesNo {
        return yesNo
            .run { blackjackInputValidator.validateYesNoString(this) }
            .run { YesNo.from(this) ?: YesNo.N }
    }

    private fun BlackjackGameProxy.fetchPlayerInfos(): List<PlayerInfo> {
        return this
            .getPlayers()
            .players
            .map { PlayerInfo.from(it) }
    }

    companion object {
        private const val PLAYER_NAME_SEPARATOR = ","
    }
}

package blackjack

import blackjack.domain.component.BlackjackInputValidator
import blackjack.domain.model.PlayerName
import blackjack.view.BlackjackInputView

class BlackjackController(
    private val blackJackInputView: BlackjackInputView,
    private val blackjackInputValidator: BlackjackInputValidator,
) {
    fun getPlayerNames(): List<PlayerName> {
        val playerNamesString: String? = blackJackInputView.getPlayerNames()

        return convertPlayerNamesStringToList(playerNamesString)
    }

    private fun convertPlayerNamesStringToList(playerNames: String?): List<PlayerName> {
        return playerNames
            .run { blackjackInputValidator.validatePlayerNamesString(this) }
            .split(PLAYER_NAME_SEPARATOR)
            .run { blackjackInputValidator.validatePlayerNamesSize(this) }
            .map { PlayerName(it) }
    }

    companion object {
        private const val PLAYER_NAME_SEPARATOR = ","
    }
}

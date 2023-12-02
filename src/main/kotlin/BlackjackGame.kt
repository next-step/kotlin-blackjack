import card.deck.GameDeck
import player.Player
import player.PlayerGroup
import player.Status
import view.InputView
import view.OutputView

class BlackjackGame(private val cardDeck: GameDeck, val playerGroup: PlayerGroup) {

    init {
        for (player in playerGroup.playerList) {
            settingCard(player)
        }
    }

    fun start() {
        for (player in playerGroup.playerList) {
            gamePlay(player)
        }
    }

    private fun gamePlay(player: Player) {
        while (player.status == Status.PLAYING) {
            val response = InputView.askForHit(player.name)
            handleForResponse(response, player)
            OutputView.showPlayingCard(player)
        }
    }

    private fun handleForResponse(response: String, player: Player) {
        when (response.uppercase()) {
            TEXT_ANSWER_YES-> {
                player.saveCard(cardDeck.getCardWithIncrease())
            }
            TEXT_ANSWER_NO -> {
                player.playDone()
            }
            else -> {
                println(TEXT_RETRY_INPUT)
            }
        }
    }

    private fun settingCard(player: Player) {
        repeat(2) {
            player.saveCard(cardDeck.getCardWithIncrease())
        }
    }

    companion object {
        private const val TEXT_RETRY_INPUT = "Y 혹은 N만 입력 가능합니다."
        private const val TEXT_ANSWER_YES = "Y"
        private const val TEXT_ANSWER_NO = "N"
    }
}

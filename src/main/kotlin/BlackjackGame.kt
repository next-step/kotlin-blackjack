import card.deck.CardDeck
import player.Player
import player.PlayerGroup
import player.Status
import view.InputViewInterface
import view.OutputViewInterface

class BlackjackGame(
    private val cardDeck: CardDeck,
    val playerGroup: PlayerGroup,
    private val inputView: InputViewInterface,
    private val outputView: OutputViewInterface,
) {

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
            val response = inputView.askForHit(player.name)
            handleForResponse(response, player)
            outputView.showPlayingCard(player)
        }
    }

    private fun handleForResponse(response: String, player: Player) {
        when (response.uppercase()) {
            TEXT_ANSWER_YES -> {
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

package blackjack.domain.game

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus
import blackjack.util.FIRST_DRAW_NUMBER

class Game(playerNames: String) {
    private val players: List<Player>
    private val dealer: Dealer
    private var playable: Boolean

    init {
        require(playerNames.isNotBlank())
        players = playerNames.split(PLAYER_INPUT_DELIMITER).map { Player(it) }
        dealer = Dealer()
        playable = true
    }

    fun start(
        printFirstTurn: (players: List<Player>) -> Unit,
        printPlayerInfo: (player: Player) -> Unit,
        inputHitDecision: (player: Player) -> Boolean,
        printResult: (players: List<Player>) -> Unit
    ) {
        firstTurn()
        printFirstTurn(players)
        while (playable) {
            playable = play(printPlayerInfo, inputHitDecision)
        }
        printResult(players)
    }

    fun firstTurn() {
        players.forEach { player ->
            val cards = dealer.drawCards(FIRST_DRAW_NUMBER)
            player.addCards(*cards.toTypedArray())
        }
    }

    private fun play(
        printPlayerInfo: (player: Player) -> Unit,
        inputHitDecision: (player: Player) -> Boolean
    ): Boolean {
        if (!playable) {
            return false
        }
        players.forEach { player -> player.turn(printPlayerInfo, inputHitDecision) }
        return players.any { player -> player.status == PlayerStatus.HIT }
    }

    private fun Player.turn(
        printPlayerInfo: (player: Player) -> Unit,
        inputHitDecision: (player: Player) -> Boolean
    ) {
        if (status != PlayerStatus.HIT) {
            return
        }

        val isHit = inputHitDecision(this)
        if (isHit) {
            val card = dealer.drawOneCard()
            addCards(card)
        } else {
            status = PlayerStatus.STAND
        }
        printPlayerInfo(this)
    }

    companion object {
        private const val PLAYER_INPUT_DELIMITER = ","
    }
}

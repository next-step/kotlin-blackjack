package blackjack.domain.game

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus
import blackjack.util.inputPlayerHitDecision

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

    fun start() {
        mulligan()
        while (playable) {
            playable = play()
        }
        end()
    }

    private fun mulligan() {
        players.forEach { player ->
            val cards = dealer.drawCards(FIRST_DRAW_NUMBER)
            player.addCards(*cards.toTypedArray())
        }
        println()
        println("${players.joinToString { it.name }} 에게 $FIRST_DRAW_NUMBER 장의 카드를 나누었습니다.")
        println(players.joinToString("\n"))
        println()
    }

    private fun play(): Boolean {
        if (!playable) {
            return false
        }
        players.forEach { player -> player.turn() }
        return players.any { player -> player.status == PlayerStatus.HIT }
    }

    private fun end() {
        println()
        players.forEach { player -> println("$player - 결과: ${player.score()}") }
    }

    private fun Player.turn() {
        if (status != PlayerStatus.HIT) {
            return
        }

        val isHit = inputPlayerHitDecision(this)
        if (isHit) {
            val card = dealer.drawOneCard()
            addCards(card)
        } else {
            status = PlayerStatus.STAND
        }
        println(this)
    }

    companion object {
        private const val FIRST_DRAW_NUMBER = 2
        private const val PLAYER_INPUT_DELIMITER = ","
    }
}

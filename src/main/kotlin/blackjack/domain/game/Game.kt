package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.ParticipantStatus

class Game(playerNames: String) {
    private val players: List<Player>
    private val dealer: Dealer
    private var playable: Boolean
    private val result: Result

    init {
        require(playerNames.isNotBlank()) { "플레이어의 이름은 공백일 수 없습니다." }
        players = playerNames.split(PLAYER_INPUT_DELIMITER).map { Player(it) }
        dealer = Dealer()
        playable = true
        result = Result()
    }

    fun start(
        printInitialHand: (players: List<Player>) -> Unit,
        printPlayerInfo: (player: Player) -> Unit,
        decideHitDecision: (player: Player) -> Boolean,
        printResult: (players: List<Player>) -> Unit
    ) {
        initialHand(printInitialHand)
        while (playable) {
            playable = play(printPlayerInfo, decideHitDecision)
        }
        printResult(players)
    }

    private fun initialHand(printFirstTurn: (players: List<Player>) -> Unit) {
        players.forEach { player ->
            val cards = dealer.drawCards(FIRST_DRAW_NUMBER)
            player.addCards(*cards.toTypedArray())
        }
        printFirstTurn(players)
    }

    private fun play(
        printPlayerInfo: (player: Player) -> Unit,
        decideHitDecision: (player: Player) -> Boolean
    ): Boolean {
        if (!playable) {
            return false
        }
        players.forEach { player -> player.turn(printPlayerInfo, decideHitDecision) }
        return players.any { it.isDrawable() }
    }

    private fun Player.turn(
        printPlayerInfo: (player: Player) -> Unit,
        decideHitDecision: (player: Player) -> Boolean
    ) {
        if (isDrawable()) {
            return
        }

        val isHit = decideHitDecision(this)
        if (isHit) {
            val card = dealer.drawOneCard()
            addCards(card)
        } else {
            changeStatus(ParticipantStatus.STAND)
        }
        printPlayerInfo(this)
    }

    companion object {
        private const val PLAYER_INPUT_DELIMITER = ","
        const val ACE_MIN_NUMBER: Int = 1
        const val ACE_MAX_NUMBER: Int = 11
        const val FIRST_DRAW_NUMBER = 2
        val ALL_CARDS = CardSuit.values().flatMap { suit ->
            CardSymbol.values().map { symbol ->
                Card(suit, symbol)
            }
        }
    }
}

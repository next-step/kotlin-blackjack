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
    private val result: Result

    init {
        require(playerNames.isNotBlank()) { "플레이어의 이름은 공백일 수 없습니다." }
        players = playerNames.split(PLAYER_INPUT_DELIMITER).map { Player(it) }
        dealer = Dealer()
        result = Result()
    }

    fun start(
        printInitialHand: (dealer: Dealer, players: List<Player>) -> Unit,
        printPlayerInfo: (player: Player) -> Unit,
        decideHitDecision: (player: Player) -> Boolean,
        printDealerDrawOneCard: () -> Unit,
        printResult: (dealer: Dealer, players: List<Player>, result: Result) -> Unit
    ) {
        initialHand(printInitialHand)
        play(printPlayerInfo, decideHitDecision, printDealerDrawOneCard)
        printResult(dealer, players, result)
    }

    private fun initialHand(printFirstTurn: (dealer: Dealer, players: List<Player>) -> Unit) {
        val dealerCards = dealer.drawCards(FIRST_DRAW_NUMBER)
        dealer.addCards(*dealerCards.toTypedArray())
        players.forEach { player ->
            val cards = dealer.drawCards(FIRST_DRAW_NUMBER)
            player.addCards(*cards.toTypedArray())
        }
        printFirstTurn(dealer, players)
    }

    private fun play(
        printPlayerInfo: (player: Player) -> Unit,
        decideHitDecision: (player: Player) -> Boolean,
        printDealerDrawOneCard: () -> Unit
    ) {
        players.forEach { player -> player.turn(printPlayerInfo, decideHitDecision) }
        dealer.turn(printDealerDrawOneCard)

        players.forEach { player -> result.check(dealer, player) }
    }

    private fun Player.turn(
        printPlayerInfo: (player: Player) -> Unit,
        decideHitDecision: (player: Player) -> Boolean
    ) {
        do {
            val isHit = decideHitDecision(this)
            if (isHit) {
                val card = dealer.drawOneCard()
                addCards(card)
            } else {
                changeStatus(ParticipantStatus.STAND)
            }
            printPlayerInfo(this)
        } while (isDrawable())
    }

    private fun Dealer.turn(printDealerDrawOneCard: () -> Unit) {
        while (isDrawable()) {
            val card = dealer.drawOneCard()
            dealer.addCards(card)
            printDealerDrawOneCard()
        }
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

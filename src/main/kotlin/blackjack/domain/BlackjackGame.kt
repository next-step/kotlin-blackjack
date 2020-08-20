package blackjack.domain

const val SPLIT_CHARACTER = ","
const val HIT = "y"
const val STAY = "n"
val HIT_OR_STAY_REGULAR_EXPRESSION = "[{$HIT|$STAY}]".toRegex()
val PLAYER_REGULAR_EXPRESSION = "^[a-z|A-Z가-힣][a-z|A-Z가-힣,]*[a-z|A-Z가-힣]$".toRegex()

class BlackjackGame(
    val players: Players,
    private val cardDeck: CardDeck = CardDeck(),
    private val betMoneyMap: BetMoneyMap = BetMoneyMap()
) {
    var isEnd: Boolean = false
        private set

    constructor(players: Players, betMoneyMap: BetMoneyMap) : this(
        players,
        CardDeck(),
        betMoneyMap
    )

    fun startGame() {
        players.allPlayersReceivedCards(cardDeck)
    }

    fun hitOrStay(isHit: String): Player? {
        if (!HIT_OR_STAY_REGULAR_EXPRESSION.matches(isHit)) return null
        players.currentPlayerPickCard(isHit == HIT, cardDeck)
        return players.currentPlayer
    }

    fun nextTurn() {
        isEnd = players.getNextPlayer() === null
        if (isEnd) players.calculateResult()
    }

    fun getPlayerProfitMoney(player: Player): Int {
        return player.playResult
            .rateOfReturn
            .times(
                betMoneyMap.getBetMoney(player.name).money
            )
            .toInt()
    }
}

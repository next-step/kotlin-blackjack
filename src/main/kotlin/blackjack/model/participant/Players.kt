package blackjack.model.participant

import blackjack.model.card.Cards
import blackjack.model.profit.Profits
import blackjack.model.state.State

data class Players(private val _players: List<Player>) {
    val players: List<Player>
        get() = _players.deepCopy()

    constructor(playerNames: String, readBetMoney: (String) -> Int) : this(
        playerNames.split(PLAYER_NAMES_DELIMITER)
            .filter { !it.isBlank() }
            .map { name -> Player(PlayerInfo(name.trim(), readBetMoney(name))) }
    )

    fun takeDefaultCards(pickCards: () -> Cards): Players = Players(players.map { it.takeDefaultCards(pickCards) })

    fun getProfits(dealerState: State): Profits = Profits(players.map { it.profit(dealerState) })

    fun size() = players.size

    fun cardsDetails(): String = players.joinToString("\n") { "${it}카드: ${it.cardsDetail()}" }

    fun scoreDetails(): String = players.joinToString("\n") { "${it}카드: ${it.cardsDetail()} - 결과: ${it.score()}" }

    private fun List<Player>.deepCopy(): List<Player> = this.map { it.copy() }

    override fun toString(): String = players.joinToString()

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
    }
}

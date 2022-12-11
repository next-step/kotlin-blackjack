package model

class Players(
    val players: List<Player>
) {
    constructor(playerNames: String) : this(playerNames.split(",").map { Player(it) })

    fun hit(dealer: Dealer) = players.forEach { player -> player.hit(dealer.pick()) }

    fun competeWith(dealer: Dealer): CompeteResult {
        val lose = players.map { it.competeWith(dealer) }.count { it == BlackJackGameResult.WIN }
        val win = players.map { it.competeWith(dealer) }.count { it == BlackJackGameResult.LOSE }
        val draw = players.map { it.competeWith(dealer) }.count { it == BlackJackGameResult.DRAW }

        return CompeteResult(win, draw, lose)
    }
}

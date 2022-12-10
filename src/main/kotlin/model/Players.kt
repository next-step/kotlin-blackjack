package model

class Players(
    val players: List<Player>
) {
    constructor(playerNames: String) : this(playerNames.split(",").map { Player(it) })

    fun hit(dealer: Dealer) = players.forEach { player -> player.hit(dealer.pick()) }

    fun competeWith(dealer: Dealer): CompeteResult {
        val lose = players.map { it.competeWith(dealer) }.count { it == WinOrLose.WIN }
        val win = players.map { it.competeWith(dealer) }.count { it == WinOrLose.LOSE }

        return CompeteResult(win, lose)
    }
}

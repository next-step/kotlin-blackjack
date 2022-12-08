package model

class Players(
    val players: List<Player>
) {
    constructor(playerNames: String) : this(playerNames.split(",").map { Player(it) })

    fun hit(dealer: Dealer) = players.forEach { player -> player.hit(dealer.pick()) }

    fun winOrLose(dealer: Dealer): Pair<Int, Int> {
        val win = players.map { it.winOrLose(dealer) }.count { it == WinOrLose.WIN }
        val lose = players.map { it.winOrLose(dealer) }.count { it == WinOrLose.LOSE }

        return win to lose
    }
}

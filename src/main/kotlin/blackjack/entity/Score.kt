package blackjack.entity

import kotlin.math.abs

object Score {
    val dealerScore = mutableMapOf<String, Int>("win" to 0, "lose" to 0)
    val playerScore = mutableListOf<Pair<String, String>>()
    private const val STANDARD_NUMBER = 21

    fun compare(player: Person, dealer: Person) {
        if (dealer.getWalletSum()> 21) {
            playerWin(player)
            return
        }
        if (getDistance(player.getWalletSum()) <= getDistance(dealer.getWalletSum())) {
            playerWin(player)
            return
        }
        dealerWin(player)
        return
    }

    fun dealerWin(player: Person) {
        playerScore.add(Pair(player.name, "패"))
        dealerScore["win"] = dealerScore["win"]?.plus(1) ?: 0
    }

    fun playerWin(player: Person) {
        playerScore.add(Pair(player.name, "승"))
        dealerScore["lose"] = dealerScore["lose"]?.plus(1) ?: 0
    }

    fun getDistance(sum: Int): Int {
        return abs(sum - STANDARD_NUMBER)
    }
}

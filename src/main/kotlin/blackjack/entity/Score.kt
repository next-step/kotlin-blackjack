package blackjack.entity

import kotlin.math.abs

object Score {
    val dealerScore = mutableMapOf<String, Int>("win" to 0, "lose" to 0)
    val playerScore = mutableListOf<Pair<String, String>>()
    private const val STANDARD_NUMBER = 21

    fun compare(player: Person, dealer: Person){
        if(getDistance(player.getWalletSum())<=getDistance(dealer.getWalletSum())){
            playerWin()
            return
        }
        dealerWin()
        return
    }

    fun dealerWin(){
        playerScore.add(Pair(player.name, "lose"))
        dealerScore["win"] = dealerScore["win"]?.plus(1) ?: 0
    }

    fun playerWin(){
        playerScore.add(Pair(player.name, "win"))
        dealerScore["lose"] = dealerScore["lose"]?.plus(1) ?: 0
    }

    fun getDistance(sum: Int): Int{
        return abs(sum-STANDARD_NUMBER)
    }
}
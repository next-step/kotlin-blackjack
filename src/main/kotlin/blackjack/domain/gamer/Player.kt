package blackjack.domain.gamer

import blackjack.dto.GeneratePlayerRequest

class Player private constructor(name: String, private val bettingMoney: Int) : BlackJackGamer(name) {

    override fun getGamerType(): GamerType {
        return GamerType.PLAYER
    }

    fun blackJackMoney(): Int {
        val blackJackWinMoney = (bettingMoney * 1.5).toInt()
        super.takeMoney(blackJackWinMoney)
        return blackJackWinMoney
    }

    fun winMoney(): Int {
        super.takeMoney(bettingMoney)
        return bettingMoney
    }

    fun loseMoney(): Int {
        super.takeMoneyOut(bettingMoney)
        return bettingMoney
    }

    fun drawMoney() {
        super.takeMoney(bettingMoney)
    }

    companion object {
        fun generatePlayer(generatePlayerRequest: GeneratePlayerRequest): Player {
            return Player(generatePlayerRequest.playerName, generatePlayerRequest.bettingMoney)
        }
    }
}

package blackjack.domain.gamer

import blackjack.dto.GeneratePlayerRequest

class Player private constructor(name: String, private val bettingMoney: Int) : BlackJackGamer(name) {
    private lateinit var gameRecord: GameRecordType

    override fun getGamerType(): GamerType {
        return GamerType.PLAYER
    }
    override fun proceedGameRecord(gameRecordType: GameRecordType) {
        gameRecord = gameRecordType
    }

    fun getGameRecord(): GameRecordType {
        require(::gameRecord.isInitialized) { "승패가 결정난 뒤에 조회가 가능합니다." }
        return gameRecord
    }

    fun blackJackMoney(): Int {
        require(::gameRecord.isInitialized) { "승패가 결정난 뒤에 돈을 정산받습니다." }
        val blackJackWinMoney = (bettingMoney * 1.5).toInt()
        money += blackJackWinMoney
        return blackJackWinMoney
    }

    fun winMoney(): Int {
        require(::gameRecord.isInitialized) { "승패가 결정난 뒤에 돈을 정산받습니다." }
        money += bettingMoney
        return bettingMoney
    }

    fun loseMoney(): Int {
        require(::gameRecord.isInitialized) { "승패가 결정난 뒤에 돈을 정산받습니다." }
        money -= bettingMoney
        return bettingMoney
    }

    fun drawMoney() {
        require(::gameRecord.isInitialized) { "승패가 결정난 뒤에 돈을 정산받습니다." }
        money += bettingMoney
    }

    companion object {
        fun generatePlayer(generatePlayerRequest: GeneratePlayerRequest): Player {
            return Player(generatePlayerRequest.playerName, generatePlayerRequest.bettingMoney)
        }
    }
}

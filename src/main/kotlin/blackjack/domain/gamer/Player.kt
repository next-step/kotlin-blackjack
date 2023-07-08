package blackjack.domain.gamer

import blackjack.dto.GeneratePlayerRequest

class Player private constructor(name: String, private val money: Int) : BlackJackGamer(name) {
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

    companion object {
        fun generatePlayer(generatePlayerRequest: GeneratePlayerRequest): Player {
            return Player(generatePlayerRequest.playerName, generatePlayerRequest.bettingMoney)
        }
    }
}

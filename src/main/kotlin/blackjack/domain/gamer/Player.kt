package blackjack.domain.gamer

class Player(private val name: String) : BlackJackGamer() {
    private lateinit var gameRecord: GameRecordType

    override fun getGamerType(): GamerType {
        return GamerType.PLAYER
    }

    override fun getName(): String {
        return name
    }

    override fun proceedGameRecord(gameRecordType: GameRecordType) {
        gameRecord = gameRecordType
    }

    fun getGameRecord(): GameRecordType {
        require(::gameRecord.isInitialized) { "승패가 결정난 뒤에 조회가 가능합니다." }
        return gameRecord
    }

    companion object {
        fun generatePlayers(nameList: List<String>): List<Player> {
            val playerList = mutableListOf<Player>()
            nameList.forEach {
                playerList.add(Player(it))
            }
            return playerList.toList()
        }
    }
}

package blackjack.model.player

sealed interface PlayerRecord {
    val player: Player
    val earnMoney: Int

    data class GuestWin(override val player: Player.Guest, override val earnMoney: Int) : PlayerRecord
    data class GuestLose(override val player: Player.Guest, override val earnMoney: Int) : PlayerRecord
    data class GuestDraw(override val player: Player.Guest, override val earnMoney: Int) : PlayerRecord

    data class DealerRecord(
        override val player: Player.Dealer,
        val win: Int = 0,
        val lose: Int = 0,
        val draw: Int = 0,
        override val earnMoney: Int
    ) : PlayerRecord
}

data class PlayerRecords(val playerRecordList: List<PlayerRecord>) : List<PlayerRecord> by playerRecordList

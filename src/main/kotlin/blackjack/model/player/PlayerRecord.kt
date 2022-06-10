package blackjack.model.player

import blackjack.model.card.State

sealed interface PlayerRecord {
    val player: Player

    data class GuestWin(override val player: Player.Guest) : PlayerRecord
    data class GuestLose(override val player: Player.Guest) : PlayerRecord

    data class GuestDraw(override val player: Player.Guest) : PlayerRecord

    data class DealerRecord(
        override val player: Player.Dealer,
        val win: Int = 0,
        val lose: Int = 0,
        val draw: Int = 0
    ) : PlayerRecord
}

data class PlayerRecords(val playerRecordList: List<PlayerRecord>) : List<PlayerRecord> by playerRecordList {

    companion object {

        fun of(dealer: Player.Dealer, guests: Players): PlayerRecords {
            val guestLit = guests.filterIsInstance<Player.Guest>()
            val guestRecords = guestLit.map { createGuestRecord(dealer.state, it) }
            val dealerRecord = createDealerRecord(dealer, guestRecords)
            return PlayerRecords(listOf(dealerRecord) + (guestRecords))
        }

        private fun createDealerRecord(dealer: Player.Dealer, guestRecords: List<PlayerRecord>): PlayerRecord {
            val drawCount = guestRecords.count { it is PlayerRecord.GuestDraw }
            val dealerLostCount = guestRecords.count { it is PlayerRecord.GuestWin }
            val dealerWinCount = guestRecords.count() - dealerLostCount - drawCount
            return PlayerRecord.DealerRecord(dealer, win = dealerWinCount, lose = dealerLostCount, draw = drawCount)
        }

        private fun createGuestRecord(dealerState: State, guest: Player.Guest): PlayerRecord {
            val guestState = guest.state
            return when {
                dealerState is State.Bust -> PlayerRecord.GuestWin(guest)
                guestState is State.Bust -> PlayerRecord.GuestLose(guest)
                guestState.finalScore > dealerState.finalScore -> PlayerRecord.GuestWin(guest)
                guestState.finalScore < dealerState.finalScore -> PlayerRecord.GuestLose(guest)
                else -> PlayerRecord.GuestDraw(guest)
            }
        }
    }
}

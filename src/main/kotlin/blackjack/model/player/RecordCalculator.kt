package blackjack.model.player

import blackjack.model.card.State

class RecordCalculator(
    private val dealer: Player.Dealer,
    private val guests: Players,
    private val initialCardCountForEachPlayer: Int
) {

    fun calculate(): PlayerRecords {
        val guestLit = guests.filterIsInstance<Player.Guest>()
        val guestRecords = guestLit.map { createGuestRecord(dealer.state, it) }
        val dealerRecord = createDealerRecord(guestRecords)
        return PlayerRecords(listOf(dealerRecord) + (guestRecords))
    }

    private fun createDealerRecord(guestRecords: List<PlayerRecord>): PlayerRecord {
        val drawCount = guestRecords.count { it is PlayerRecord.GuestDraw }
        val dealerLostCount = guestRecords.count { it is PlayerRecord.GuestWin }
        val dealerWinCount = guestRecords.count() - dealerLostCount - drawCount
        val dealerEarnMoney = guestRecords.sumOf { -it.earnMoney }
        return PlayerRecord.DealerRecord(
            dealer,
            win = dealerWinCount,
            lose = dealerLostCount,
            draw = drawCount,
            earnMoney = dealerEarnMoney
        )
    }

    private fun createGuestRecord(dealerState: State, guest: Player.Guest): PlayerRecord {
        val guestState = guest.state
        val guestBetMoney = guest.betMoney
        val guestCardCount = guest.cardCount

        return when {
            guestState is State.BlackJack && guestCardCount == initialCardCountForEachPlayer -> {
                createGuestRecordWhenGuestBlackJack(dealerState = dealerState, guest = guest)
            }
            dealerState is State.Bust -> {
                PlayerRecord.GuestWin(player = guest, earnMoney = guestBetMoney)
            }
            guestState is State.Bust -> {
                PlayerRecord.GuestLose(player = guest)
            }
            guestState.finalScore > dealerState.finalScore -> {
                PlayerRecord.GuestWin(player = guest, earnMoney = guestBetMoney)
            }
            guestState.finalScore < dealerState.finalScore -> {
                PlayerRecord.GuestLose(player = guest)
            }
            else -> {
                PlayerRecord.GuestDraw(player = guest)
            }
        }
    }

    private fun createGuestRecordWhenGuestBlackJack(dealerState: State, guest: Player.Guest): PlayerRecord {
        require(guest.state is State.BlackJack)
        return if (dealerState is State.BlackJack)
            PlayerRecord.GuestDraw(guest)
        else
            PlayerRecord.GuestWin(player = guest, earnMoney = (guest.betMoney * REWARD_RATIO_OF_BLACK_JACK).toInt())
    }

    companion object {
        const val REWARD_RATIO_OF_BLACK_JACK = 1.5
    }
}

package blackjack.model.player

import blackjack.model.card.State
import blackjack.model.player.PlayerRecord.Companion.recordWith

sealed interface PlayerRecord {
    val player: Player
    val earnMoney: Int

    data class GuestWin(override val player: Player.Guest) : PlayerRecord {
        override val earnMoney: Int
            get() {
                val shouldApplyRewardRatio = (player.state is State.BlackJack && !player.hasAdditionalCards)
                return if (shouldApplyRewardRatio) {
                    (player.betMoney * REWARD_RATIO_OF_BLACK_JACK).toInt()
                } else {
                    player.betMoney
                }
            }
    }

    data class GuestLose(override val player: Player.Guest) : PlayerRecord {
        override val earnMoney: Int
            get() = -player.betMoney
    }

    data class GuestDraw(override val player: Player.Guest) : PlayerRecord {
        override val earnMoney: Int
            get() = 0
    }

    data class DealerRecord(
        override val player: Player.Dealer,
        val win: Int = 0,
        val lose: Int = 0,
        val draw: Int = 0,
        override val earnMoney: Int
    ) : PlayerRecord

    companion object {

        const val REWARD_RATIO_OF_BLACK_JACK = 1.5

        fun Player.Guest.recordWith(dealer: Player.Dealer): PlayerRecord {
            val dealerState = dealer.state
            val guestState = this.state

            return when {
                dealerState is State.Bust -> GuestWin(player = this)
                guestState is State.Bust -> GuestLose(player = this)
                guestState.finalScore > dealerState.finalScore -> GuestWin(player = this)
                guestState.finalScore < dealerState.finalScore -> GuestLose(player = this)
                else -> GuestDraw(player = this)
            }
        }

        fun Player.Dealer.recordWith(guestRecords: List<PlayerRecord>): DealerRecord {
            val drawCount = guestRecords.count { it is GuestDraw }
            val dealerLostCount = guestRecords.count { it is GuestWin }
            val dealerWinCount = guestRecords.count() - dealerLostCount - drawCount
            val dealerEarnMoney = guestRecords.sumOf { -it.earnMoney }
            return DealerRecord(
                this,
                win = dealerWinCount,
                lose = dealerLostCount,
                draw = drawCount,
                earnMoney = dealerEarnMoney
            )
        }
    }
}

data class PlayerRecords(val playerRecordList: List<PlayerRecord>) : List<PlayerRecord> by playerRecordList {

    companion object {
        fun of(dealer: Player.Dealer, guests: Players<Player.Guest>): PlayerRecords {
            val guestRecords = guests.map { guest -> guest.recordWith(dealer = dealer) }
            val dealerRecord = dealer.recordWith(guestRecords = guestRecords)
            return PlayerRecords(listOf(dealerRecord) + (guestRecords))
        }
    }
}

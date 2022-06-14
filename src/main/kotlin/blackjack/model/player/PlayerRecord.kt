package blackjack.model.player

import blackjack.model.card.State
import blackjack.model.player.PlayerRecord.Companion.recordWith

sealed interface PlayerRecord {
    val player: Player
    val earnMoney: Int

    data class GuestWin(val playerBetBetMoney: PlayerBet) : PlayerRecord {

        override val player: Player
            get() = playerBetBetMoney.player

        override val earnMoney: Int
            get() {
                val shouldApplyRewardRatio = (player.state is State.BlackJack && !player.hasAdditionalCards)
                return if (shouldApplyRewardRatio) {
                    (playerBetBetMoney.betMoney * REWARD_RATIO_OF_BLACK_JACK).toInt()
                } else {
                    playerBetBetMoney.betMoney
                }
            }
    }

    data class GuestLose(val playerBetBetMoney: PlayerBet) : PlayerRecord {

        override val player: Player
            get() = playerBetBetMoney.player

        override val earnMoney: Int
            get() = -playerBetBetMoney.betMoney
    }

    data class GuestDraw(val playerBetBetMoney: PlayerBet) : PlayerRecord {

        override val player: Player
            get() = playerBetBetMoney.player

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

        fun PlayerBet.recordWith(dealer: Player.Dealer): PlayerRecord {
            val dealerState = dealer.state
            val guestState = this.player.state

            return when {
                dealerState is State.Bust -> GuestWin(playerBetBetMoney = this)
                guestState is State.Bust -> GuestLose(playerBetBetMoney = this)
                guestState.finalScore > dealerState.finalScore -> GuestWin(playerBetBetMoney = this)
                guestState.finalScore < dealerState.finalScore -> GuestLose(playerBetBetMoney = this)
                else -> GuestDraw(playerBetBetMoney = this)
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
        fun of(dealer: Player.Dealer, guestBets: PlayerBets): PlayerRecords {
            val guestRecords = guestBets.map { guestBet -> guestBet.recordWith(dealer = dealer) }
            val dealerRecord = dealer.recordWith(guestRecords = guestRecords)
            return PlayerRecords(listOf(dealerRecord) + (guestRecords))
        }
    }
}

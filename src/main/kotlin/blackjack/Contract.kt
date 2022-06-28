package blackjack

import blackjack.domain.BetAmount
import blackjack.domain.BlackjackGameResult
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.PlayerName

interface Contract {
    interface View {
        fun getPlayerNames(): List<PlayerName>

        fun getBetAmount(playerName: PlayerName): BetAmount

        fun isPlayerWannaHit(player: Player): Boolean

        fun showStartOfGameInfo(participants: Participants)

        fun showPlayersHitEvent(player: Player)

        fun showDealerHitEvent()

        fun showEndOfGameInfo(blackjackGameResult: BlackjackGameResult)
    }

    interface Presenter {
        fun startGame()
    }
}

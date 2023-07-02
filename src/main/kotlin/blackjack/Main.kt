package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.Round
import blackjack.domain.RoundStatus
import blackjack.ui.BlackJackPlayerNameReader
import blackjack.ui.GameResultPrinter
import blackjack.ui.PlayerHandPrinter
import blackjack.ui.RoundAnswer
import blackjack.ui.RoundMessagePrinter

fun main() {
    val dealer = Dealer.create()
    val playerNames: List<PlayerName> = BlackJackPlayerNameReader.read()
    val players: List<Player> = playerNames.map { Player.of(it, dealer.open()) }

    RoundMessagePrinter.open(playerNames)

    players.forEach { PlayerHandPrinter.print(it) }

    players
        .map { Round(dealer, it) }
        .forEach { round ->
            while(round.status != RoundStatus.FINISH) {
                val answer = RoundMessagePrinter.hitOrStay(round.player.name)
                if (answer == RoundAnswer.y) {
                    round.next()
                    PlayerHandPrinter.print(round.player)
                } else {
                    round.stop()
                }
            }
        }
    players.forEach { GameResultPrinter.print(it) }
}

package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.ui.BlackJackPlayerNameReader
import blackjack.ui.BlackJackRoundManager
import blackjack.ui.GameResultPrinter
import blackjack.ui.PlayerHandPrinter

fun main() {
    val dealer = Dealer.create()
    val playerNames: List<PlayerName> = BlackJackPlayerNameReader.read2()
    val players: List<Player> = playerNames.map { Player.of(it, dealer.open()) }

    val blackJackRoundManager = BlackJackRoundManager(dealer)
    blackJackRoundManager.printOpenMessage(playerNames)
    players.forEach { PlayerHandPrinter.print(it) }
    players.forEach { blackJackRoundManager.round(it) }
    players.forEach { GameResultPrinter.print(it) }
}

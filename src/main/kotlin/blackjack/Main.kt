package blackjack

import blackjack.domain.Dealer
import blackjack.ui.BlackJackPlayerReader
import blackjack.ui.BlackJackRoundManager
import blackjack.ui.GameResultPrinter
import blackjack.ui.PlayerHandPrinter

fun main() {
    val dealer = Dealer.create()
    val players = BlackJackPlayerReader.read(dealer)

    players.forEach { PlayerHandPrinter.print(it) }

    val blackJackRoundManager = BlackJackRoundManager(dealer)
    players.forEach { blackJackRoundManager.round(it) }

    players.forEach { GameResultPrinter.print(it) }
}

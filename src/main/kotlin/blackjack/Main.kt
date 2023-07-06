package blackjack

import blackjack.domain.Decision
import blackjack.domain.GameResultManager
import blackjack.domain.HitDecision
import blackjack.domain.card.ShuffledCardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.ui.BlackJackPlayerNameReader
import blackjack.ui.DealerMessagePrinter
import blackjack.ui.DecisionReader
import blackjack.ui.GameResultPrinter
import blackjack.ui.HandPrinter
import blackjack.ui.RoundMessagePrinter

fun main() {

    val dealer = Dealer.of(PlayerName.from("딜러"), ShuffledCardDeck())
    dealer.openSelf()

    val playerNames: List<PlayerName> = BlackJackPlayerNameReader.read()
    val players: List<Player> = playerNames.map { Player.of(it, dealer.open()) }

    RoundMessagePrinter.open(playerNames)
    DealerMessagePrinter.hand(dealer)
    players.forEach { HandPrinter.printAll(it.name, it.hand) }

    players.forEach { player ->
        val playerName: PlayerName = player.name
        while (!player.isFinished()) {
            val decision: Decision = DecisionReader.read(playerName)
            decision.process(dealer, player)
            if (decision is HitDecision) {
                HandPrinter.printAll(playerName, player.hand)
            }
        }
    }

    if (dealer.shouldHit()) {
        DealerMessagePrinter.shouldHit()
        dealer.hitSelf()
    }

    GameResultPrinter.ofPlayer(dealer)
    players.forEach { GameResultPrinter.ofPlayer(it) }

    val gameResult = GameResultManager.calculate(dealer, players)
    GameResultPrinter.summary(gameResult)
}

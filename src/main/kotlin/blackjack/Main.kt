package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Decision
import blackjack.domain.GameResultManager
import blackjack.domain.HitDecision
import blackjack.domain.Money
import blackjack.domain.StayDecision
import blackjack.domain.card.ShuffledCardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.ui.BlackJackPlayerNameReader
import blackjack.ui.DealerMessagePrinter
import blackjack.ui.DecisionReader
import blackjack.ui.GameResultPrinter
import blackjack.ui.HandPrinter
import blackjack.ui.MoneyReader
import blackjack.ui.RoundMessagePrinter

fun main() {

    val dealer = Dealer(ShuffledCardDeck())

    val playerNames: List<PlayerName> = BlackJackPlayerNameReader.read()

    val players: List<Player> = playerNames.map {
        val money: Money = MoneyReader.read(it)
        Player(it, money)
    }

    dealer.open(dealer.fetchOpenCard())
    players.forEach { it.open(dealer.fetchOpenCard()) }

    RoundMessagePrinter.open(playerNames)
    DealerMessagePrinter.hand(dealer)
    players.forEach { HandPrinter.printAll(it.name, it.hand) }

    players.forEach { player ->
        val playerName: PlayerName = player.name
        while (!player.isFinished()) {
            val decision: Decision = DecisionReader.read(playerName)
            if (decision is HitDecision) {
                dealer.dealing(player)
                HandPrinter.printAll(playerName, player.hand)
            }
            if (decision is StayDecision) {
                player.stay()
            }
        }
    }

    if (dealer.shouldHit()) {
        DealerMessagePrinter.shouldHit()
        dealer.dealing(dealer)
    }

    GameResultPrinter.dealer(dealer)
    players.forEach { GameResultPrinter.player(it) }

    val gameResult = GameResultManager.calculate(dealer, players)
    GameResultPrinter.summary(gameResult)
}

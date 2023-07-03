package blackjack

import blackjack.domain.Dealer
import blackjack.domain.GameResultManager
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.Round
import blackjack.domain.RoundStatus
import blackjack.domain.ShuffledCardDeck
import blackjack.ui.BlackJackPlayerNameReader
import blackjack.ui.GameResultPrinter
import blackjack.ui.HandPrinter
import blackjack.ui.RoundAnswer
import blackjack.ui.RoundMessagePrinter

fun main() {

    val dealer = Dealer.of(PlayerName.from("딜러"), ShuffledCardDeck())
    dealer.openSelf()

    //
    val playerNames: List<PlayerName> = BlackJackPlayerNameReader.read()
    val players: List<Player> = playerNames.map { Player.of(it, dealer.open()) }

    RoundMessagePrinter.open(playerNames)
    HandPrinter.printFirstCard(dealer.name, dealer.hand)
    players.forEach { HandPrinter.printAll(it.name, it.hand) }

    players
        .map { Round(dealer, it) }
        .forEach { round ->
            val playerName: PlayerName = round.player.name
            while(round.status != RoundStatus.FINISH) {
                val answer = RoundMessagePrinter.hitOrStay(playerName)
                if (answer == RoundAnswer.y) {
                    round.next()
                    HandPrinter.printAll(playerName, round.player.hand)
                } else {
                    round.stop()
                }
            }
        }

    // 딜러 16 이하 시 힛
    if (dealer.total() <= 16) {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        dealer.dealing(dealer)
    }

    GameResultPrinter.print(dealer)
    players.forEach { GameResultPrinter.print(it) }

    val (dealerResult, playerResults) = GameResultManager.calculate(dealer, players)

    println("## 최종 승패")
    println("딜러: ${dealerResult.win}승 ${dealerResult.lose}패")
    playerResults.forEach {
        println("${it.player.name.value}: ${it.result.displayName}")
    }

}

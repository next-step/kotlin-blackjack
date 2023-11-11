package blackjack

import blackjack.domain.*
import blackjack.view.InputView
import blackjack.view.OutputView

fun main(args: Array<String>) {
    val dealer = Dealer(deck(RandomShuffleStrategy) {
        Rank.values().flatMap { value -> Symbol.values() of value }.map { +it }
    })

    val nicknames = InputView.readNicknames()
    val players = nicknames.map { Player(it, dealer) }

    players.map { player -> repeat(2) { player.receiveCard() } }
    OutputView.writePlayerNames(players)
    OutputView.writePlayerCards(*players.toTypedArray())

    players.map { player ->
        while (InputView.readHitOrStand(player.name) == PlayerDecision.HIT) {
            player.receiveCard()
            OutputView.writePlayerCards(player)
        }
    }

    OutputView.writePlayerResults(players)
}

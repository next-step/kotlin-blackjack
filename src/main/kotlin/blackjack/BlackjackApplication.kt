package blackjack

import blackjack.domain.*
import blackjack.view.InputView
import blackjack.view.OutputView

fun main(args: Array<String>) {
    val dealer = Dealer(deck(RandomShuffleStrategy) {
        Rank.values().flatMap { value -> Suit.values() of value }.map { +it }
    })

    val nicknames = InputView.readNicknames()
    val players = nicknames.map { Player(it, dealer) }
    OutputView.writePlayers(players)
}

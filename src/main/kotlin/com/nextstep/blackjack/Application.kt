package com.nextstep.blackjack

import com.nextstep.blackjack.domain.Player
import com.nextstep.blackjack.domain.Players
import com.nextstep.blackjack.domain.card.CardDeck
import com.nextstep.blackjack.view.InputView
import com.nextstep.blackjack.view.OutputView

private const val YES = "y"
private val inputView = InputView()
private val outputView = OutputView()

fun main() {
    val names = inputView.inputNames()
    val players = Players(names)
    val cards = CardDeck()

    dealFirstCards(cards, players)

    outputView.printAfterDealing(players)
    outputView.printStatus(*players.players)

    dealExtraCards(players, cards)

    outputView.printResult(*players.players)
}

private fun dealFirstCards(cards: CardDeck, players: Players) {
    cards.deal(*players.players)
    cards.deal(*players.players)
}

private fun dealExtraCards(players: Players, cards: CardDeck) = players.players.forEach { dealExtraCard(it, cards) }

private fun dealExtraCard(player: Player, cards: CardDeck) {
    while (player.canTakeMoreCard() && wantMoreCard(player)) {
        cards.deal(player)
        outputView.printStatus(player)
    }
}

private fun wantMoreCard(player: Player) = inputView.askMoreCard(player.name) == YES

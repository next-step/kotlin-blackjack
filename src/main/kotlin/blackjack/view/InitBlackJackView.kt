package blackjack.view

import blackjack.domain.BlackJack

fun initBlackJackView(blackJack: BlackJack) {
    println("\n" + blackJack.players.toPlayerNamesString() + "에게 2장의 나누었습니다.")
    blackJack.players.players.forEach { println(it.playerName.name + "카드: " + it.cardsToString()) }
}

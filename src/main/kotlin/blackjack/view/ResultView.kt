package blackjack.view

import blackjack.domain.BlackJack

fun resultView(blackJack: BlackJack) {
    println()
    blackJack.players.players.forEach {
        println(it.playerName.name + "카드: " + it.mutableCards.cardsToString() + " - 결과: " + it.sumCardValues())
    }
}

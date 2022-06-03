package com.nextstep.jngcii.blackjack.domain

object GameRunner {
    fun run(deck: CardDeck, player: PlayerBoard, moreable: Boolean): Boolean {
        if (!moreable) return false

        player.addCard(deck.pop())

        return true
    }
}

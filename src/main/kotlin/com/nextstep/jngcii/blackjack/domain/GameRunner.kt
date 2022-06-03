package com.nextstep.jngcii.blackjack.domain

object GameRunner {
    private const val LIMIT = 21

    fun run(deck: CardDeck, player: PlayerBoard, moreable: Boolean): Boolean {
        if (!moreable) return false

        player.addCard(deck.pop())

        if (player.total > LIMIT) return false

        return true
    }
}

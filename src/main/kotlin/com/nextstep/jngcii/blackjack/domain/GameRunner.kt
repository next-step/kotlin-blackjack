package com.nextstep.jngcii.blackjack.domain

object GameRunner {
    private const val LIMIT = 21

    fun run(
        deck: CardDeck,
        board: PlayerBoard,
        getMoreable: (name: String) -> Boolean,
        printState: (board: PlayerBoard) -> Unit
    ) {
        while (true) {
            val addable = board.total <= LIMIT && getMoreable(board.playerName)
            if (addable) {
                board.addCard(deck.pop())
            }
            printState(board)
            if (!addable) break
        }
    }
}

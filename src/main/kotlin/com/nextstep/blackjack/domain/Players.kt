package com.nextstep.blackjack.domain

class Players(val players: List<Player>) {
    fun initState(deck: Deck) {
        require(deck.isInitialState()) { "초기화 할 수 없는 상태입니다" }
        players.forEach {
            it.draw(deck.initialDraw())
        }
    }
}

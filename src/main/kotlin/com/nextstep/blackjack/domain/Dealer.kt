package com.nextstep.blackjack.domain

class Dealer(var deck: Deck) : BasePlayer("딜러") {
    init {
        require(deck.isInitialState()) { "딜러는 초기화 상태의 덱으로 초기화해야합니다." }
    }

    fun initStage(players: Players) {
        _cards.addAll(deck.initialDraw())
        players.initState(deck)
    }

    fun draw() {
        _cards.addAll(listOf(deck.draw()))
    }

    fun isUpperThreshold(): Boolean {
        return calculateScore() > BlackJackConstants.DEALER_UPPER_BOUND
    }

    fun getBeatPlayer(players: Players): List<Player> {
        if (calculateScore() > BlackJackConstants.BLACKJACK_NUMBER) {
            return emptyList()
        }
        return players.players
            .filter { !it.isBust() }
            .filter { it.calculateScore() <= calculateScore() }
    }
}

package com.nextstep.blackjack.domain

data class Player(override val name: String) : BasePlayer(name) {
    fun isBeatDealer(dealer: Dealer): Boolean {
        if (isBust()) {
            return false
        }
        if (dealer.isBust()) {
            return true
        }
        return calculateScore() > dealer.calculateScore()
    }
}

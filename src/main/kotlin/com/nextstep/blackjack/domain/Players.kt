package com.nextstep.blackjack.domain

import com.nextstep.blackjack.domain.card.CardDeck

class Players(names: List<String>) {
    val players: List<Player> = names.map { Player(it) }

    fun names() = players.map { it.name }
}

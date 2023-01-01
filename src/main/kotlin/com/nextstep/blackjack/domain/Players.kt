package com.nextstep.blackjack.domain

import com.nextstep.blackjack.domain.card.CardDeck

class Players(names: List<String>) {
    val players: List<Player> = names.map { Player(it) }

    fun dealCards(cards: CardDeck) = players.forEach { it.addCard(cards.draw()) }

    fun names() = players.map { it.name }
}

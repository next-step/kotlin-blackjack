package com.nextstep.blackjack.domain

class Players(names: List<String>) {
    val players: Array<Player> = names.map { Player(it) }.toTypedArray()

    constructor(vararg names: String) : this(names.toList())

    fun names() = players.map { it.name }
}

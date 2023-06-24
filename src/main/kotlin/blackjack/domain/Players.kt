package blackjack.domain

import blackjack.domain.card.Card

@JvmInline
value class Players(
    val values: List<Player>
) {
    init {
        require(values.isNotEmpty()) { "플레이어는 최소 1명이 되어야한다." }
    }

    fun drawAllPlayer(receiveCard: () -> Card) = values.forEach { it.draw(receiveCard()) }

    fun allPlayersDrawResult(): List<DrawResult> = values.map { it.currentStatus() }

    fun isEndTurn(index: Int): Boolean = values.size == index

    companion object {
        fun from(names: List<String>) = Players(names.map { Player(it) })
    }
}

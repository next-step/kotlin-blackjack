package com.nextstep.blackjack.domain

class Deck(cards: List<Card>) {
    private val _cards = cards.shuffled().toMutableList()
    fun initialDraw(): List<Card> {
        require(_cards.size > 2) { "deck이 2장 미만입니다." }

        return listOf(draw(), draw())
    }

    fun draw(): Card {
        require(_cards.isNotEmpty()) { "deck이 비었습니다." }

        return _cards.removeFirst()
    }

    fun size(): Int {
        return _cards.size
    }

    fun isInitialState(): Boolean {
        return _cards.size == SIZE
    }

    companion object {
        const val SIZE = 52
        private val CARDS = CardNumber.values()
            .flatMap { cardNumber ->
                Symbol.values().map { Card(it, cardNumber) }
            }
            .toList()

        fun createDeck(): Deck {
            return Deck(CARDS.toList())
        }
    }
}

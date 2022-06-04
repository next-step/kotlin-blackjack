package com.nextstep.jngcii.blackjack.domain

class CardDeck {
    private val cards = Card
        .Shape.values()
        .flatMap { shape ->
            Card.SYMBOL
                .values()
                .map { symbol -> Card(shape, symbol) }
        }.shuffled().toMutableList()

    fun pop() = cards.removeFirstOrNull()
        ?: throw IllegalStateException("카드가 동났습니다")
}

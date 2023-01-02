package com.nextstep.blackjack.domain.card

import com.nextstep.blackjack.domain.Players
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize

class CardDeckTest : FunSpec({
    test("CardDeck은 player에게 카드를 나눠줄 수 있다") {
        val cardDeck = CardDeck()
        val players = Players(listOf("수진", "쪼밀리"))
        players.players[0].cards shouldHaveSize 0
        players.players[1].cards shouldHaveSize 0

        cardDeck.deal(*players.players.toTypedArray())

        players.players[0].cards shouldHaveSize 1
        players.players[1].cards shouldHaveSize 1
    }
})

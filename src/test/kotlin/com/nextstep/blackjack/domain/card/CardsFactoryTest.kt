package com.nextstep.blackjack.domain.card

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize

class CardFactoryTest : FunSpec({
    test("CardFactory로 52개의 서로 다른 카드를 받을 수 있다") {
        val cardDeck = CardFactory.getCards()
        cardDeck.toHashSet() shouldHaveSize 52
    }
})

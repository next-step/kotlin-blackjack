package com.nextstep.blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class PlayersTest : FunSpec({
    context("Players") {
        test("52장의 덱이 아닌경우 초기화시 예외발생") {
            val players = Players(listOf(Player("j"), Player("k")))

            val deck = Deck.createDeck()
            deck.draw()

            val exception = shouldThrow<IllegalArgumentException> { players.initState(deck) }
            exception.message shouldBe "초기화 할 수 없는 상태입니다"
        }
    }
})

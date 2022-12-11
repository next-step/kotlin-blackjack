package com.nextstep.blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DeckTest : FunSpec({
    context("Deck") {
        test("최초 덱 52장 확인") {
            val deck = Deck.createDeck()

            deck.size() shouldBe 52
        }

        test("최초 드로우 2장 확인") {
            val deck = Deck.createDeck()

            deck.initialDraw().size shouldBe 2
        }

        test("최초 이후 게임진행시 일반적인 드로우 1장 확인") {
            val deck = Deck.createDeck()
            deck.draw()

            deck.size() shouldBe 51
        }

        test("최초 드로우 2장시 카드가 2장 미만이라면 예외 발생") {
            val deck = Deck.createDeck()
            repeat(51) {
                deck.draw()
            }

            val exception = shouldThrow<IllegalArgumentException> { deck.initialDraw() }
            exception.message shouldBe "deck이 2장 미만입니다."
        }

        test("최초 이후 게임진행시 일반적인 드로우 시 덱이 비어있다면 예외 발생") {
            val deck = Deck.createDeck()
            repeat(52) {
                deck.draw()
            }

            val exception = shouldThrow<IllegalArgumentException> { deck.draw() }
            exception.message shouldBe "deck이 비었습니다."
        }
    }
})

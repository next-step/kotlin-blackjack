package com.nextstep.blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    context("Player") {
        test("카드 draw를 하고 bust가 아닌 경우.") {
            listOf(
                listOf(
                    Card(Symbol.DIAMOND, CardNumber.ACE),
                    Card(Symbol.CLOVER, CardNumber.ACE)
                ),
                listOf(
                    Card(Symbol.DIAMOND, CardNumber.ACE),
                    Card(Symbol.CLOVER, CardNumber.ACE)
                )
            ).forAll {
                val player = Player("jay")
                player.draw(it)

                player.isBust() shouldBe false
            }
        }

        test("카드 draw를 하고 bust인 경우.") {
            listOf(
                listOf(
                    Card(Symbol.DIAMOND, CardNumber.KING),
                    Card(Symbol.SPADE, CardNumber.KING),
                    Card(Symbol.CLOVER, CardNumber.KING)
                ),
                listOf(
                    Card(Symbol.DIAMOND, CardNumber.JACK),
                    Card(Symbol.SPADE, CardNumber.QUEEN),
                    Card(Symbol.CLOVER, CardNumber.QUEEN)
                )
            ).forAll {
                val player = Player("jay")
                player.draw(it)

                player.isBust() shouldBe true
            }
        }

        test("카드 draw를 하고 점수 계산") {
            listOf(
                row(
                    listOf(
                        Card(Symbol.DIAMOND, CardNumber.KING),
                        Card(Symbol.SPADE, CardNumber.KING)
                    ),
                    20
                ),
                row(
                    listOf(
                        Card(Symbol.DIAMOND, CardNumber.ACE),
                        Card(Symbol.SPADE, CardNumber.KING)
                    ),
                    21
                ),
                row(
                    listOf(
                        Card(Symbol.DIAMOND, CardNumber.FIVE),
                        Card(Symbol.SPADE, CardNumber.KING)
                    ),
                    15
                ),
                row(
                    listOf(
                        Card(Symbol.DIAMOND, CardNumber.ACE),
                        Card(Symbol.SPADE, CardNumber.TEN),
                        Card(Symbol.SPADE, CardNumber.TEN)
                    ),
                    21
                )
            ).forAll { (cards, expected) ->
                val player = Player("jay")
                player.draw(cards)
                player.calculateScore() shouldBe expected
            }
        }
    }
})

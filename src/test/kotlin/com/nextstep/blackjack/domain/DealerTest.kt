package com.nextstep.blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class DealerTest : FunSpec({
    context("Delaer") {
        test("딜러 게임 상한선 16 테스트") {
            listOf(
                row(
                    listOf(Card(Symbol.CLOVER, CardNumber.THREE)),
                    true
                ),
                row(
                    listOf(
                        Card(Symbol.CLOVER, CardNumber.QUEEN),
                        Card(Symbol.CLOVER, CardNumber.KING)
                    ),
                    false
                )
            ).forAll { (cards, expected) ->
                run {
                    val dealer = Dealer(Deck.createDeck())
                    dealer.draw(cards)
                    dealer.isUpperThreshold() == expected
                }
            }
        }
        test("플레이어와 승패 테스트") {
            listOf(
                row(
                    listOf(Card(Symbol.CLOVER, CardNumber.THREE)),
                    listOf(Card(Symbol.CLOVER, CardNumber.FOUR)),
                    emptyList<Player>()
                ),
                row(
                    listOf(Card(Symbol.CLOVER, CardNumber.FOUR)),
                    listOf(Card(Symbol.CLOVER, CardNumber.THREE)),
                    listOf(Player("jay"))
                )
            ).forAll { (dealerCards, playerCards, expected) ->
                run {
                    val dealer = Dealer(Deck.createDeck())
                    dealer.draw(dealerCards)

                    val player = Player("jay")
                    player.draw(playerCards)

                    val beatPlayers = dealer.getBeatPlayer(Players(listOf(player)))
                    beatPlayers shouldBe expected
                }
            }
        }
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
                val dealer = Dealer(Deck.createDeck())
                dealer.draw()

                dealer.isBust() shouldBe false
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
                val dealer = Dealer(Deck.createDeck())
                dealer.draw(it)

                dealer.isBust() shouldBe true
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
                val dealer = Dealer(Deck.createDeck())
                dealer.draw(cards)
                dealer.calculateScore() shouldBe expected
            }
        }
    }
})

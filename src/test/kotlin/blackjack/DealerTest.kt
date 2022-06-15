package blackjack

import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Symbol
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerTest : DescribeSpec({

    describe("canDraw") {
        context("카드의 점수 합이 16이하이면 ") {
            it("true 를 리턴한다.") {
                val player = Dealer(PlayerCards(CardNumber.Num6, CardNumber.Num10))
                player.canDraw() shouldBe true
            }
        }
        context("카드의 점수 합이 17이상이면 ") {
            it("false 를 리턴한다.") {
                val player = Dealer(PlayerCards(CardNumber.Num7, CardNumber.Num10))
                player.canDraw() shouldBe false
            }
        }
    }

    describe("openedCards") {
        context("두 장 이상을 가지고 있어도") {
            it("한장만 오픈 한다.") {
                val player = Dealer(PlayerCards(CardNumber.Num6, CardNumber.Num10))
                player.openedCards().size shouldBe 1
            }
        }
    }

    describe("getBetResult") {
        context("버스트인 경우") {
            it("배팅을 전부 잃는다.") {
                val dealerCards = PlayerCards(CardNumber.Num4, CardNumber.Jack)
                val playerCards = PlayerCards(CardNumber.Num4, CardNumber.Jack, CardNumber.Num10)

                val dealer = Dealer(dealerCards)
                val player = Player(name = "", playerCards = playerCards)
                val actual = dealer.getPlayerBetResult(player)

                actual.earnMoneyRatio shouldBe -1.0f
            }
        }

        context("첫 두장으로 블랙잭인 경우") {
            it("배팅의 1.5배 를 받는다.") {
                val dealerCards = PlayerCards(CardNumber.Num4, CardNumber.Jack)
                val playerCards = PlayerCards(CardNumber.Ace, CardNumber.Jack)

                val dealer = Dealer(dealerCards)
                val player = Player(name = "", playerCards = playerCards)
                val actual = dealer.getPlayerBetResult(player)

                actual.earnMoneyRatio shouldBe 1.5f
            }
        }

        context("딜러와 플레이어가 동시에 블랙잭인 경우") {
            it("배팅 금액을 받는다.") {
                val dealerCards = PlayerCards(CardNumber.Ace, CardNumber.Jack)
                val playerCards = PlayerCards(CardNumber.Ace, CardNumber.Jack)

                val dealer = Dealer(dealerCards)
                val player = Player(name = "", playerCards = playerCards)
                val actual = dealer.getPlayerBetResult(player)

                actual.earnMoneyRatio shouldBe 1.0f
            }
        }

        context("딜러가 버스트인 경우") {
            it("남아있는 플레이어는 배팅 금액을 받는다.") {
                val dealerCards = PlayerCards(CardNumber.Jack, CardNumber.Num4, CardNumber.Num10)
                val playerCards = PlayerCards(CardNumber.Ace, CardNumber.Num5)

                val dealer = Dealer(dealerCards)
                val player = Player(name = "", playerCards = playerCards)
                val actual = dealer.getPlayerBetResult(player)

                actual.earnMoneyRatio shouldBe 1.0f
            }
        }

        context("딜러가 17점인 경우") {
            it("18점 이상인 플레이어는 배팅 금액을 받는다.") {
                val dealerCards = PlayerCards(CardNumber.Num10, CardNumber.Num7)
                val playerCards = PlayerCards(CardNumber.Num10, CardNumber.Num8)

                val dealer = Dealer(dealerCards)
                val player = Player(name = "", playerCards = playerCards)
                val actual = dealer.getPlayerBetResult(player)

                actual.earnMoneyRatio shouldBe 1.0f
            }
        }

        context("딜러가 17점인 경우") {
            it("16점 이하인 플레이어는 배팅을 전부 잃는다.") {
                val dealerCards = PlayerCards(CardNumber.Num10, CardNumber.Num7)
                val playerCards = PlayerCards(CardNumber.Num10, CardNumber.Num6)

                val dealer = Dealer(dealerCards)
                val player = Player(name = "", playerCards = playerCards)
                val actual = dealer.getPlayerBetResult(player)

                actual.earnMoneyRatio shouldBe -1.0f
            }
        }
    }
})

private fun PlayerCards(vararg cards: CardNumber) = blackjack.domain.PlayerCards(cards.toList().map { Card(it) })
private fun Card(number: CardNumber) = blackjack.domain.Card(Symbol.Diamond, number)

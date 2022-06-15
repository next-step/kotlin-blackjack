package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.DealerRule
import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.domain.PlayerCards
import blackjack.domain.Players
import blackjack.domain.Symbol
import blackjack.domain.WinningDiscriminator
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class WinningDiscriminatorTest : DescribeSpec({
    describe("discrimination") {
        forAll(
            row(20000, -20000),
            row(-20000, 20000),
            row(0, 0)
        ) { playerBetMoney, dealerExpectedMoney ->
            context("플레이어가 총 수익이 20000원을 인 경우") {
                it("딜러는 -20000원을 갇는다.") {
                    val betMoney = Money(playerBetMoney)
                    val expected = Money(dealerExpectedMoney)

                    val dealerCards = PlayerCards(CardNumber.Num2)
                    val playerCards = PlayerCards(CardNumber.Num3)

                    val dealer = Dealer(dealerCards)
                    val player = Player(name = "", betMoney = betMoney, playerCards = playerCards)

                    val results = WinningDiscriminator.discrimination(dealer, Players(player))

                    val dealerResult = results[0]

                    dealerResult.earnMoney shouldBe expected
                }
            }
        }
    }

    describe("getBetResult") {
        context("버스트인 경우") {
            it("배팅을 전부 잃는다.") {
                val money = Money(10000)
                val expected = Money(-10000)

                val dealerCards = PlayerCards(CardNumber.Num4, CardNumber.Jack)
                val playerCards = PlayerCards(CardNumber.Num4, CardNumber.Jack, CardNumber.Num10)
                val actual = WinningDiscriminator.getBetResult(money, dealerCards, playerCards)
                actual shouldBe expected
            }
        }

        context("첫 두장으로 블랙잭인 경우") {
            it("배팅의 1.5배 를 받는다.") {
                val money = Money(10000)
                val expected = Money(15000)

                val dealerCards = PlayerCards(CardNumber.Num4, CardNumber.Jack)
                val playerCards = PlayerCards(CardNumber.Ace, CardNumber.Jack)
                val actual = WinningDiscriminator.getBetResult(money, dealerCards, playerCards)
                actual shouldBe expected
            }
        }

        context("딜러와 플레이어가 동시에 블랙잭인 경우") {
            it("배팅 금액을 받는다.") {
                val money = Money(10000)
                val expected = Money(10000)

                val dealerCards = PlayerCards(CardNumber.Ace, CardNumber.Jack)
                val playerCards = PlayerCards(CardNumber.Ace, CardNumber.Jack)
                val actual = WinningDiscriminator.getBetResult(money, dealerCards, playerCards)
                actual shouldBe expected
            }
        }

        context("딜러가 버스트인 경우") {
            it("남아있는 플레이어는 배팅 금액을 받는다.") {
                val money = Money(10000)
                val expected = Money(10000)

                val dealerCards = PlayerCards(CardNumber.Jack, CardNumber.Num4, CardNumber.Num10)
                val playerCards = PlayerCards(CardNumber.Ace, CardNumber.Num5)
                val actual = WinningDiscriminator.getBetResult(money, dealerCards, playerCards)
                actual shouldBe expected
            }
        }

        context("딜러가 17점인 경우") {
            it("18점 이상인 플레이어는 배팅 금액을 받는다.") {
                val money = Money(10000)
                val expected = Money(10000)

                val dealerCards = PlayerCards(CardNumber.Num10, CardNumber.Num7)
                val playerCards = PlayerCards(CardNumber.Num10, CardNumber.Num8)
                val actual = WinningDiscriminator.getBetResult(money, dealerCards, playerCards)
                actual shouldBe expected
            }
        }

        context("딜러가 17점인 경우") {
            it("16점 이하인 플레이어는 배팅을 전부 잃는다.") {
                val money = Money(10000)
                val expected = Money(-10000)

                val dealerCards = PlayerCards(CardNumber.Num10, CardNumber.Num7)
                val playerCards = PlayerCards(CardNumber.Num10, CardNumber.Num6)
                val actual = WinningDiscriminator.getBetResult(money, dealerCards, playerCards)
                actual shouldBe expected
            }
        }
    }
})

private fun PlayerCards(vararg cards: CardNumber) = PlayerCards(cards.toList().map { Card(it) })
private fun Players(vararg player: Player) = Players(player.toList())
private fun Dealer(card: PlayerCards) = Player("딜러", card, rule = DealerRule)
private fun Card(number: CardNumber) = Card(Symbol.Diamond, number)

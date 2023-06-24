package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.FaceType
import blackjack.domain.Gamer
import blackjack.domain.Money
import blackjack.domain.Players
import blackjack.domain.SymbolType
import blackjack.domain.dsl.buildDeck
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class OutcomeStateContextHolderTest : BehaviorSpec({

    val blackJackDealer = Dealer(
        buildDeck {
            aceCards(SymbolType.HEART)
            faceCards {
                SymbolType.HEART to FaceType.KING
            }
        }
    )
    val burstDealer = Dealer(
        buildDeck {
            aceCards(SymbolType.HEART)
            numberCards {
                7..8 from SymbolType.HEART
            }
        }
    )

    val stayDealer = Dealer(
        buildDeck {
            numberCards {
                5..7 from SymbolType.CLOVER
            }
        }
    )

    Given("게이머가 있다. ") {
        val betAmount = 1000.0
        var gamer: Gamer
        var dealer: Dealer

        When("게이머가 블랙잭이라면") {
            gamer = Gamer(
                name = "Catsbi",
                deck = buildDeck {
                    aceCards(SymbolType.DIAMOND)
                    faceCards {
                        SymbolType.DIAMOND to FaceType.KING
                    }
                },
                bet = betAmount
            )

            And("딜러의 상태가 블랙잭만 아니라면") {
                Then("1.5배의 베팅금액을 받는다.") {
                    listOf(burstDealer, stayDealer).forEach {
                        gamer.revenue = Money()
                        it.calculate(Players(listOf(gamer)))

                        gamer.revenue shouldBe Money(betAmount * 1.5)
                    }
                }
            }

            And("딜러의 상태도 블랙잭이라면") {
                Then(" 베팅 금액을 돌려받는다.") {
                    gamer.revenue = Money()
                    blackJackDealer.calculate(Players(listOf(gamer)))

                    gamer.revenue shouldBe Money()
                }
            }
        }

        When("게이머가 버스트라면") {
            gamer = Gamer(
                name = "Catsbi",
                deck = buildDeck {
                    aceCards(SymbolType.DIAMOND)
                    numberCards {
                        8..9 from SymbolType.HEART
                    }
                },
                bet = betAmount
            )

            And("딜러의 상태가 버스트가 아닐 경우 ") {
                val dealers = listOf(blackJackDealer, stayDealer)

                Then("게이머는 베팅 금액을 잃는다.") {
                    dealers.forEach {
                        gamer.revenue = Money()
                        it.revenue = Money()
                        it.calculate(Players(listOf(gamer)))

                        gamer.revenue shouldBe Money(-betAmount)
                        it.revenue shouldBe Money(betAmount)
                    }
                }
            }
        }

        When("게이머가 스테이라면") {
            gamer = Gamer(
                name = "Catsbi",
                deck = buildDeck {
                    numberCards {
                        8..9 from SymbolType.HEART
                    }
                },
                bet = betAmount
            )

            And("딜러도 스테이이면서 더 낮은 점수일 경우") {
                gamer.revenue = Money()
                dealer = Dealer(
                    deck = buildDeck {
                        numberCards { 2..4 from SymbolType.DIAMOND }
                    }
                )

                Then("게이머는 베팅 금액만큼을 딜러에게 받는다.") {
                    dealer.calculate(Players(listOf(gamer)))

                    gamer.revenue shouldBe Money(betAmount)
                    dealer.revenue shouldBe Money(-betAmount)
                }
            }

            And("딜러도 스테이면서 더 높은 점수일 경우") {
                gamer.revenue = Money()
                dealer = Dealer(
                    deck = buildDeck {
                        faceCards {
                            SymbolType.DIAMOND to FaceType.KING
                        }
                        numberCards { 5..6 from SymbolType.DIAMOND }
                    }
                )

                Then("게이머는 베팅 금액만큼을 딜러에게 전한다.") {
                    dealer.calculate(Players(listOf(gamer)))

                    gamer.revenue shouldBe Money(-betAmount)
                    dealer.revenue shouldBe Money(betAmount)
                }
            }
        }
    }
})

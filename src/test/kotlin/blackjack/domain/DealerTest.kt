package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({

    Given("딜러 객체는") {
        var dealer: Dealer

        When("점수가 16점 이하이고") {
            dealer = Dealer(
                deck = Deck().apply {
                    add(NumberCard(symbol = SymbolType.CLOVER, number = 9))
                    add(NumberCard(symbol = SymbolType.CLOVER, number = 2))
                }
            )

            And("최초 분배 이후 분배받은적이 없는 경우") {
                Then("카드를 추가할 수 있다.") {
                    dealer.isAddable() shouldBe true

                    dealer.addCard(AceCard(SymbolType.HEART))

                    dealer.calculateScore() shouldBe 12
                }
            }
        }

        When("점수가 16점 이하이고 최초 분배 이후 분배받은적이 있는 경우") {
            dealer = Dealer(
                deck = Deck().apply {
                    add(NumberCard(symbol = SymbolType.CLOVER, number = 9))
                    add(NumberCard(symbol = SymbolType.CLOVER, number = 2))
                }
            )
            dealer.addCard(NumberCard(symbol = SymbolType.HEART, number = 2))

            Then("카드를 추가할 수 없다.") {
                dealer.isAddable() shouldBe false

                shouldThrow<IllegalArgumentException> {
                    dealer.addCard(AceCard(SymbolType.HEART))
                }
            }
        }

        When("점수가 16점 초과이면서 최초 분배 이후 분배받은적이 없는 경우") {
            dealer = Dealer(
                deck = Deck().apply {
                    add(NumberCard(symbol = SymbolType.CLOVER, number = 9))
                    add(NumberCard(symbol = SymbolType.HEART, number = 8))
                }
            )

            Then("카드를 추가할 수 없다.") {
                dealer.isAddable() shouldBe false

                shouldThrow<IllegalArgumentException> {
                    dealer.addCard(AceCard(SymbolType.HEART))
                }
            }
        }
        When("점수가 16점 초과이면서 최초 분배 이후 분배받은적이 있는 경우") {
            dealer = Dealer(
                deck = Deck().apply {
                    add(NumberCard(symbol = SymbolType.CLOVER, number = 9))
                    add(NumberCard(symbol = SymbolType.CLOVER, number = 6))
                }
            )

            dealer.addCard(NumberCard(symbol = SymbolType.HEART, number = 2))

            Then("카드를 추가할 수 없다.") {
                dealer.isAddable() shouldBe false

                shouldThrow<IllegalArgumentException> {
                    dealer.addCard(AceCard(SymbolType.HEART))
                }
            }
        }
    }
})

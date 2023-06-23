package blackjack.domain

import blackjack.domain.dsl.buildDeck
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

    Given("딜러와 플레이어들이 있다.") {
        var dealer: Dealer
        var players: Players

        When("딜러가 21점을 초과하면") {
            dealer = Dealer()
            players = Players(listOf(Gamer(name = "catsbi"), Gamer("pobi")))

            dealer.addCardAll(
                listOf(
                    FaceCard(symbol = SymbolType.HEART, faceType = FaceType.JACK),
                    NumberCard(symbol = SymbolType.HEART, number = 5),
                    NumberCard(symbol = SymbolType.DIAMOND, number = 9)

                )
            )
            players.forEach {
                it.addCard(NumberCard(symbol = SymbolType.HEART, number = (2..9).random()))
            }

            Then("플레이어어 점수에 상관없이 승리로 평가한다.") {
                val actual = dealer.calculate(players = players)

                actual.dealerRecord[OutcomeType.LOSE] shouldBe 2
                actual.playerRecords.forEach {
                    it.value shouldBe OutcomeType.WIN
                }
            }
        }

        When("딜러가 21점 이하라면") {
            dealer = Dealer()
            players = Players(
                listOf(
                    Gamer(
                        name = "catsbi",
                        deck = buildDeck {
                            faceCards {
                                SymbolType.HEART to FaceType.QUEEN and FaceType.KING
                            }
                        }
                    ),
                    Gamer(
                        "pobi",
                        deck = buildDeck {
                            faceCards { SymbolType.HEART to FaceType.QUEEN }
                            numberCards {
                                2..3 from SymbolType.DIAMOND
                            }
                        }
                    )
                )
            )

            dealer.addCardAll(
                listOf(
                    FaceCard(symbol = SymbolType.HEART, faceType = FaceType.JACK),
                    NumberCard(symbol = SymbolType.HEART, number = 7)
                )
            )

            Then("각각의 플레이어와 승패를 계산한다.") {
                val gameResult = dealer.calculate(players = players)

                gameResult.dealerRecord[OutcomeType.WIN] shouldBe 1
                gameResult.dealerRecord[OutcomeType.LOSE] shouldBe 1

                gameResult.playerRecords[players[0]] shouldBe OutcomeType.WIN
                gameResult.playerRecords[players[1]] shouldBe OutcomeType.LOSE
            }
        }
    }
})

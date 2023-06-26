package next.step.blackjack.domain.dealer

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.card.Cards

class DealerTest : DescribeSpec({

    describe("Dealer method") {
        context("name") {
            it("딜러 이름 제공") {
                Dealer.of(Cards.of(emptyList())).name() shouldBe "딜러"
            }
        }
        context("cardDescFirst") {
            it("첫번째 카드 설명 제공") {
                Dealer.of(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.ACE, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    )
                ).cardDescFirst() shouldBe "A스페이드"
            }
        }
        context("cardDescs") {
            it("카드 리스트 설명 제공") {
                Dealer.of(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.ACE, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    )
                ).cardDescs() shouldBe setOf("A스페이드", "10스페이드")
            }
        }
        context("카드 점수가 16점 이하이면 받을 수 있음") {
            withData(
                listOf(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.SIX, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    ),
                    Cards.of(
                        listOf(
                            Card.of(CardFace.FIVE, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    )
                )
            ) { cards ->
                Dealer.of(cards).canHit() shouldBe true
            }
        }
        context("카드 점수가 16점 이상이면 받을 수 있음") {
            withData(
                listOf(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.SEVEN, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    ),
                    Cards.of(
                        listOf(
                            Card.of(CardFace.ACE, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    )
                )
            ) { cards ->
                Dealer.of(cards).canHit() shouldBe false
            }
        }
        context("hit") {
            it("카드를 한 장 더 받음") {
                val dealer = Dealer.of(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.SEVEN, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    )
                )

                dealer.hit(Card.of(CardFace.ACE, CardSymbol.HEART))

                dealer shouldBe Dealer.of(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.SEVEN, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE),
                            Card.of(CardFace.ACE, CardSymbol.HEART)
                        )
                    )
                )
            }
        }

        context("turn") {
            it("canHit이면 카드 더 받음") {
                val dealer = Dealer.of(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.SIX, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    )
                )

                dealer.turn({ Card.of(CardFace.ONE, CardSymbol.HEART) }, {})

                dealer shouldBe Dealer.of(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.SIX, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE),
                            Card.of(CardFace.ONE, CardSymbol.HEART)
                        )
                    )
                )
            }
            it("canHit 아니면 카드 더 안받음") {
                val dealer = Dealer.of(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.SEVEN, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    )
                )

                dealer.turn({ Card.of(CardFace.ONE, CardSymbol.HEART) }, {})

                dealer shouldBe Dealer.of(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.SEVEN, CardSymbol.SPADE),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    )
                )
            }
        }
    }
})

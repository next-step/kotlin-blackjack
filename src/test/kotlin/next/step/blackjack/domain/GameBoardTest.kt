package next.step.blackjack.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameBoardTest : DescribeSpec({
    describe("GameBoard") {
        context("게임을 시작하면") {
            it("플레이어들에게 카드를 두장씩 제공함") {
                val player1 = Player.of("dj")
                val player2 = Player.of("dj2")

                GameBoard.of(
                    GameCards.of(
                        listOf(
                            Card.of(CardFace.TWO, CardSymbol.CLUB),
                            Card.of(CardFace.THREE, CardSymbol.CLUB),
                            Card.of(CardFace.FOUR, CardSymbol.CLUB),
                            Card.of(CardFace.FIVE, CardSymbol.CLUB),
                            Card.of(CardFace.TWO, CardSymbol.CLUB)
                        )
                    ),
                    setOf(player1, player2)
                ).start { _, _ -> }

                assertSoftly {
                    player1.cards.size() shouldBe 2
                    player2.cards.size() shouldBe 2
                }
            }
        }
        context("플레이어별 턴을 돌아가면") {
            it("플레이어가 받는다고 선택하면 받을 수 있을 때까지 카드를 받음") {
                val player1 = Player.of("dj")
                val player2 = Player.of("dj2")

                GameBoard.of(
                    GameCards.of(
                        listOf(
                            Card.of(CardFace.ACE, CardSymbol.CLUB),
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.FOUR, CardSymbol.CLUB),
                            Card.of(CardFace.FIVE, CardSymbol.CLUB),
                            Card.of(CardFace.THREE, CardSymbol.CLUB),
                            Card.of(CardFace.QUEEN, CardSymbol.CLUB)
                        )
                    ),
                    setOf(player1, player2)
                ).turn({ _ -> true }, {})

                assertSoftly {
                    player1.cards shouldBe PlayerCards.of(
                        listOf(
                            Card.of(CardFace.ACE, CardSymbol.CLUB),
                            Card.of(CardFace.KING, CardSymbol.CLUB)
                        )
                    )
                    player2.cards shouldBe PlayerCards.of(
                        listOf(
                            Card.of(CardFace.FOUR, CardSymbol.CLUB),
                            Card.of(CardFace.FIVE, CardSymbol.CLUB),
                            Card.of(CardFace.THREE, CardSymbol.CLUB),
                            Card.of(CardFace.QUEEN, CardSymbol.CLUB)
                        )
                    )
                }
            }

            it("플레이어가 받는다고 선택하지 않으면 카드를 받지 않음") {
                val player1 = Player.of("dj")
                val player2 = Player.of("dj2")

                GameBoard.of(
                    GameCards.of(
                        listOf(
                            Card.of(CardFace.ACE, CardSymbol.CLUB),
                            Card.of(CardFace.KING, CardSymbol.CLUB),
                            Card.of(CardFace.FOUR, CardSymbol.CLUB),
                            Card.of(CardFace.FIVE, CardSymbol.CLUB),
                            Card.of(CardFace.THREE, CardSymbol.CLUB),
                            Card.of(CardFace.QUEEN, CardSymbol.CLUB)
                        )
                    ),
                    setOf(player1, player2)
                ).turn({ _ -> false }, {})

                assertSoftly {
                    player1.cards shouldBe PlayerCards.of(emptyList())
                    player2.cards shouldBe PlayerCards.of(emptyList())
                }
            }
        }
    }

})

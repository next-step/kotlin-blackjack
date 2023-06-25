package next.step.blackjack.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.player.Player
import next.step.blackjack.domain.player.PlayerCards
import next.step.blackjack.domain.player.PlayerName
import next.step.blackjack.domain.player.PlayerNames

class GameBoardTest : DescribeSpec({
    describe("GameBoard") {
        context("생성하면") {
            it("카드를 두장씩 가지는 플레이어들이 생성됨") {
                val gameBoard = GameBoard.of(
                    GameCards.of(
                        listOf(
                            Card.of(CardFace.TWO, CardSymbol.CLUB),
                            Card.of(CardFace.THREE, CardSymbol.CLUB),
                            Card.of(CardFace.FOUR, CardSymbol.CLUB),
                            Card.of(CardFace.FIVE, CardSymbol.CLUB),
                            Card.of(CardFace.TWO, CardSymbol.CLUB)
                        )
                    ),
                    PlayerNames(setOf(PlayerName.of("dj"), PlayerName.of("dj2")))
                )

                gameBoard.players shouldBe setOf(
                    Player.of(
                        PlayerName.of("dj"), PlayerCards.of(
                            listOf(
                                Card.of(CardFace.TWO, CardSymbol.CLUB),
                                Card.of(CardFace.THREE, CardSymbol.CLUB)
                            )
                        )
                    ),
                    Player.of(
                        PlayerName.of("dj2"), PlayerCards.of(
                            listOf(
                                Card.of(CardFace.FOUR, CardSymbol.CLUB),
                                Card.of(CardFace.FIVE, CardSymbol.CLUB)
                            )
                        )
                    )
                )
            }
        }
        context("플레이어별 턴을 돌아가면") {
            it("플레이어가 받는다고 선택하면 받을 수 있을 때까지 카드를 받음") {
                val gameBoard = GameBoard.of(
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
                    PlayerNames(setOf(PlayerName.of("dj"), PlayerName.of("dj2")))
                )

                gameBoard.turn({ _ -> true }, {})

                gameBoard.players shouldBe setOf(
                    Player.of(
                        PlayerName.of("dj"), PlayerCards.of(
                            listOf(
                                Card.of(CardFace.ACE, CardSymbol.CLUB),
                                Card.of(CardFace.KING, CardSymbol.CLUB)
                            )
                        )
                    ),
                    Player.of(
                        PlayerName.of("dj2"),
                        PlayerCards.of(
                            listOf(
                                Card.of(CardFace.FOUR, CardSymbol.CLUB),
                                Card.of(CardFace.FIVE, CardSymbol.CLUB),
                                Card.of(CardFace.THREE, CardSymbol.CLUB),
                                Card.of(CardFace.QUEEN, CardSymbol.CLUB)
                            )
                        )
                    )
                )

            }

            it("플레이어가 받는다고 선택하지 않으면 카드를 받지 않음") {
                val gameBoard = GameBoard.of(
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
                    PlayerNames(setOf(PlayerName.of("dj"), PlayerName.of("dj2")))
                )

                gameBoard.turn({ _ -> false }, {})

                gameBoard.players shouldBe setOf(
                    Player.of(
                        PlayerName.of("dj"), PlayerCards.of(
                            listOf(
                                Card.of(CardFace.ACE, CardSymbol.CLUB),
                                Card.of(CardFace.KING, CardSymbol.CLUB)
                            )
                        )
                    ),
                    Player.of(
                        PlayerName.of("dj2"),
                        PlayerCards.of(
                            listOf(
                                Card.of(CardFace.FOUR, CardSymbol.CLUB),
                                Card.of(CardFace.FIVE, CardSymbol.CLUB)
                            )
                        )
                    )
                )
            }
        }
    }
})

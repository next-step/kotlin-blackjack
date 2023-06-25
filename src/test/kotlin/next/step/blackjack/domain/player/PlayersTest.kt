package next.step.blackjack.domain.player

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.*

class PlayersTest : DescribeSpec({
    describe("Players") {
        context("생성하면") {
            val gameCards = GameCards.of(
                listOf(
                    Card.of(CardFace.TWO, CardSymbol.CLUB),
                    Card.of(CardFace.THREE, CardSymbol.CLUB),
                    Card.of(CardFace.FOUR, CardSymbol.CLUB),
                    Card.of(CardFace.FIVE, CardSymbol.CLUB),
                    Card.of(CardFace.TWO, CardSymbol.CLUB)
                )
            )
            it("카드를 두장씩 가지는 플레이어들이 생성됨") {
                val players = Players.of(
                    PlayerNames(
                        setOf(
                            PlayerName.of("dj"),
                            PlayerName.of("dj2")
                        )
                    )
                ) { gameCards.pop(it) }

                players shouldBe setOf(
                    Player.of(
                        PlayerName.of("dj"), Cards.of(
                            listOf(
                                Card.of(CardFace.TWO, CardSymbol.CLUB),
                                Card.of(CardFace.THREE, CardSymbol.CLUB)
                            )
                        )
                    ),
                    Player.of(
                        PlayerName.of("dj2"), Cards.of(
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
                val gameCards = GameCards.of(
                    listOf(
                        Card.of(CardFace.ACE, CardSymbol.CLUB),
                        Card.of(CardFace.KING, CardSymbol.CLUB),
                        Card.of(CardFace.FOUR, CardSymbol.CLUB),
                        Card.of(CardFace.FIVE, CardSymbol.CLUB),
                        Card.of(CardFace.THREE, CardSymbol.CLUB),
                        Card.of(CardFace.QUEEN, CardSymbol.CLUB)
                    )
                )
                val players = Players.of(
                    PlayerNames(
                        setOf(
                            PlayerName.of("dj"),
                            PlayerName.of("dj2")
                        )
                    )
                ) { gameCards.pop(it) }

                players.turn({ _ -> true }, { gameCards.pop() }, {})

                players shouldBe setOf(
                    Player.of(
                        PlayerName.of("dj"), Cards.of(
                            listOf(
                                Card.of(CardFace.ACE, CardSymbol.CLUB),
                                Card.of(CardFace.KING, CardSymbol.CLUB)
                            )
                        )
                    ),
                    Player.of(
                        PlayerName.of("dj2"),
                        Cards.of(
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
                val gameCards = GameCards.of(
                    listOf(
                        Card.of(CardFace.ACE, CardSymbol.CLUB),
                        Card.of(CardFace.KING, CardSymbol.CLUB),
                        Card.of(CardFace.FOUR, CardSymbol.CLUB),
                        Card.of(CardFace.FIVE, CardSymbol.CLUB),
                        Card.of(CardFace.THREE, CardSymbol.CLUB),
                        Card.of(CardFace.QUEEN, CardSymbol.CLUB)
                    )
                )
                val players = Players.of(
                    PlayerNames(
                        setOf(
                            PlayerName.of("dj"),
                            PlayerName.of("dj2")
                        )
                    )
                ) { gameCards.pop(it) }

                players.turn({ _ -> false }, { gameCards.pop() }, {})

                players shouldBe setOf(
                    Player.of(
                        PlayerName.of("dj"), Cards.of(
                            listOf(
                                Card.of(CardFace.ACE, CardSymbol.CLUB),
                                Card.of(CardFace.KING, CardSymbol.CLUB)
                            )
                        )
                    ),
                    Player.of(
                        PlayerName.of("dj2"),
                        Cards.of(
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

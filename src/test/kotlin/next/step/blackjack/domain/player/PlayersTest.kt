package next.step.blackjack.domain.player

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.betting.BettingAmount
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.dealer.Dealer
import next.step.blackjack.domain.game.GameCards
import next.step.blackjack.domain.game.GameResult
import next.step.blackjack.domain.game.GameResults

class PlayersTest : DescribeSpec({
    describe("Players") {
        context("생성") {
            val gameCards = GameCards.of(
                listOf(
                    Card.of(CardFace.TWO, CardSymbol.CLUB),
                    Card.of(CardFace.THREE, CardSymbol.CLUB),
                    Card.of(CardFace.FOUR, CardSymbol.CLUB),
                    Card.of(CardFace.FIVE, CardSymbol.CLUB),
                    Card.of(CardFace.SIX, CardSymbol.CLUB)
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
                ) { gameCards.pop(2) }

                players.cards() shouldBe listOf(
                    listOf(
                        Card.of(CardFace.TWO, CardSymbol.CLUB),
                        Card.of(CardFace.THREE, CardSymbol.CLUB),
                    ),
                    listOf(
                        Card.of(CardFace.FOUR, CardSymbol.CLUB),
                        Card.of(CardFace.FIVE, CardSymbol.CLUB),
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
                ) { gameCards.pop(2) }

                players.turn({ _ -> true }, { gameCards.pop() }, {})

                players.cards() shouldBe listOf(
                    listOf(
                        Card.of(CardFace.ACE, CardSymbol.CLUB),
                        Card.of(CardFace.KING, CardSymbol.CLUB),
                    ),
                    listOf(
                        Card.of(CardFace.FOUR, CardSymbol.CLUB),
                        Card.of(CardFace.FIVE, CardSymbol.CLUB),
                        Card.of(CardFace.THREE, CardSymbol.CLUB),
                        Card.of(CardFace.QUEEN, CardSymbol.CLUB),
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
                ) { gameCards.pop(2) }

                players.turn({ _ -> false }, { gameCards.pop() }, {})

                players.cards() shouldBe listOf(
                    listOf(
                        Card.of(CardFace.ACE, CardSymbol.CLUB),
                        Card.of(CardFace.KING, CardSymbol.CLUB),
                    ),
                    listOf(
                        Card.of(CardFace.FOUR, CardSymbol.CLUB),
                        Card.of(CardFace.FIVE, CardSymbol.CLUB),
                    )
                )
            }
        }
        context("method") {
            it("fight 하면 GameResults 제공") {
                val players = Players.of(
                    setOf(
                        Player.of(
                            PlayerName.of("stay17"),
                            Cards.of(
                                listOf(
                                    Card.of(CardFace.SEVEN, CardSymbol.CLUB),
                                    Card.of(CardFace.TEN, CardSymbol.HEART)
                                )
                            )
                        ),
                        Player.of(
                            PlayerName.of("blackjack"),
                            Cards.of(
                                listOf(
                                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                                    Card.of(CardFace.TEN, CardSymbol.HEART)
                                )
                            )
                        ),
                        Player.of(
                            PlayerName.of("hit"),
                            Cards.of(
                                listOf(
                                    Card.of(CardFace.TEN, CardSymbol.CLUB),
                                    Card.of(CardFace.TEN, CardSymbol.HEART),
                                    Card.of(CardFace.ONE, CardSymbol.HEART)
                                )
                            )
                        ),
                        Player.of(
                            PlayerName.of("burst"),
                            Cards.of(
                                listOf(
                                    Card.of(CardFace.TEN, CardSymbol.CLUB),
                                    Card.of(CardFace.TEN, CardSymbol.HEART),
                                    Card.of(CardFace.TEN, CardSymbol.SPADE)
                                )
                            )
                        )
                    )
                )
                val dealer = Dealer.of(
                    Cards.of(
                        listOf(
                            Card.of(CardFace.ACE, CardSymbol.CLUB),
                            Card.of(CardFace.TEN, CardSymbol.HEART)
                        )
                    )
                )

                players.fight(dealer) shouldBe GameResults(
                    mapOf(
                        "stay17" to GameResult.LOSE,
                        "blackjack" to GameResult.TIE,
                        "hit" to GameResult.LOSE,
                        "burst" to GameResult.LOSE,
                    ),
                    mapOf(
                        GameResult.WIN to 3,
                        GameResult.TIE to 1
                    )
                )
            }
            it("bet하면 베팅 금액을 받아서 BettingPlayers 생성") {
                val player = Player.of(
                    PlayerName.of("stay17"),
                    Cards.of(
                        listOf(
                            Card.of(CardFace.SEVEN, CardSymbol.CLUB),
                            Card.of(CardFace.TEN, CardSymbol.HEART)
                        )
                    )
                )

                val result = Players.of(setOf(player)).bet { BettingAmount.of(1000) }

                assertSoftly {
                    result.players.size shouldBe 1
                    result.players.first().player shouldBe player
                    result.players.first().amount shouldBe BettingAmount(1000)
                }
            }
        }
    }
})

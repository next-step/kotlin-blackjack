package next.step.blackjack.domain.betting

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.card.GameCards
import next.step.blackjack.domain.dealer.Dealer
import next.step.blackjack.domain.player.Player
import next.step.blackjack.domain.player.PlayerName

class BettingPlayersTest : DescribeSpec({

    describe("BettingPlayers") {
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
                val players = BettingPlayers.of(
                    setOf(
                        BettingPlayer("dj", 100),
                        BettingPlayer("dj2", 100)
                    )
                )

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
                val players = BettingPlayers.of(
                    setOf(
                        BettingPlayer("dj", 100),
                        BettingPlayer("dj2", 100)
                    )
                )

                players.turn({ _ -> false }, { gameCards.pop() }, {})

                players.cards() shouldBe listOf(emptyList(), emptyList())
            }
        }
        context("fight") {
            it("BettingResults 제공") {
                val players = BettingPlayers.of(
                    setOf(
                        BettingPlayer(
                            "unfinished17", 1000,
                            Card.of(CardFace.SEVEN, CardSymbol.CLUB),
                            Card.of(CardFace.TEN, CardSymbol.HEART)
                        ),
                        BettingPlayer(
                            "blackjack", 1000,
                            Card.of(CardFace.ACE, CardSymbol.CLUB),
                            Card.of(CardFace.TEN, CardSymbol.HEART)
                        ),
                        BettingPlayer(
                            "finished", 1000,
                            Card.of(CardFace.TEN, CardSymbol.CLUB),
                            Card.of(CardFace.TEN, CardSymbol.HEART),
                            Card.of(CardFace.ONE, CardSymbol.HEART)
                        ),
                        BettingPlayer(
                            "burst", 1000,
                            Card.of(CardFace.TEN, CardSymbol.CLUB),
                            Card.of(CardFace.TEN, CardSymbol.HEART),
                            Card.of(CardFace.TEN, CardSymbol.SPADE)
                        )
                    )
                )
                val dealer = Dealer(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.TEN, CardSymbol.HEART)
                )

                players.fight(dealer) shouldBe BettingResults(
                    mapOf(
                        "unfinished17" to -1000,
                        "blackjack" to 0,
                        "finished" to -1000,
                        "burst" to -1000,
                    ),
                    3000
                )
            }
        }
    }
})

private fun BettingPlayer(name: String, amount: Int, vararg cards: Card): BettingPlayer =
    BettingPlayer.of(Player.of(PlayerName.of(name), Cards.of(cards.toList())), BettingAmount.of(amount))

private fun Dealer(vararg cards: Card): Dealer = Dealer.of(Cards.of(cards.toList()))

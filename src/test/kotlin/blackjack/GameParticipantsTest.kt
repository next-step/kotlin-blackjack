package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameParticipantsTest : BehaviorSpec({

    Given("플레이어 2명과 딜러가 존재하고") {
        val players = listOf(
            GameParticipantPlayer(
                "winner",
                listOf(
                    Card(Card.Symbol.HEART, Card.Number.JACK),
                    Card(Card.Symbol.HEART, Card.Number.KING)
                ),
                1000
            ),
            GameParticipantPlayer(
                "loser",
                listOf(
                    Card(Card.Symbol.SPADE, Card.Number.TWO),
                    Card(Card.Symbol.SPADE, Card.Number.THREE)
                ),
                1000
            ),
        )
        When("딜러가 두 명의 플레이어에게 각각 이기고 진다면") {
            val dealer = GameParticipantDealer(
                "dealer",
                listOf(
                    Card(Card.Symbol.DIAMOND, Card.Number.JACK),
                    Card(Card.Symbol.DIAMOND, Card.Number.NINE)
                )
            )
            val matchResults = GameParticipants(players, dealer).calcMatchResult()
            Then("두 명의 플레이어는 각각 승과 패를 한다.") {
                matchResults.players shouldBe listOf(
                    GameParticipantPlayerResult("winner", MatchResult.WIN, 1000),
                    GameParticipantPlayerResult("loser", MatchResult.LOSS, 1000)
                )
            }
        }
        When("딜러가 버스트한다면") {
            val dealer = GameParticipantDealer(
                "dealer",
                listOf(
                    Card(Card.Symbol.DIAMOND, Card.Number.JACK),
                    Card(Card.Symbol.DIAMOND, Card.Number.KING),
                    Card(Card.Symbol.DIAMOND, Card.Number.TWO)
                )
            )
            val matchResults = GameParticipants(players, dealer).calcMatchResult()
            Then("두 명의 플레이어는 소유한 카드와 상관없이 승리한다.") {
                matchResults.players shouldBe listOf(
                    GameParticipantPlayerResult("winner", MatchResult.WIN, 1000),
                    GameParticipantPlayerResult("loser", MatchResult.WIN, 1000)
                )
            }
        }
    }

    Given("플레이어가 블랙잭으로 이긴다면") {
        val players = listOf(
            GameParticipantPlayer(
                "winner",
                listOf(
                    Card(Card.Symbol.HEART, Card.Number.JACK),
                    Card(Card.Symbol.HEART, Card.Number.ACE)
                ),
                1000
            )
        )
        When("딜러가 버스트 되더라도") {
            val dealer = GameParticipantDealer(
                "dealer",
                listOf(
                    Card(Card.Symbol.DIAMOND, Card.Number.JACK),
                    Card(Card.Symbol.DIAMOND, Card.Number.KING),
                    Card(Card.Symbol.DIAMOND, Card.Number.TWO)
                )
            )
            Then("1.5배의 베팅 금액만큼 수익을 낸다.") {
                val matchResults = GameParticipants(players, dealer).calcMatchResult()
                matchResults shouldBe GameParticipantResults(
                    listOf(
                        GameParticipantPlayerResult(
                            "winner",
                            MatchResult.BLACKJACK,
                            1000
                        )
                    )
                )
                matchResults.players.forEach {
                    it.betAmount * 1.5 shouldBe it.profit
                }
            }
        }
    }
})

package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameScoreCalculatorTest : BehaviorSpec({

    Given("플레이어 2명과 딜러가 존재하고") {
        val players = listOf(
            GameParticipant.Player(
                "winner",
                listOf(
                    Card(Card.Symbol.HEART, Card.Number.JACK),
                    Card(Card.Symbol.HEART, Card.Number.KING)
                )
            ),
            GameParticipant.Player(
                "loser",
                listOf(
                    Card(Card.Symbol.SPADE, Card.Number.TWO),
                    Card(Card.Symbol.SPADE, Card.Number.THREE)
                )
            ),
        )
        When("딜러가 두 명의 플레이어에게 각각 이기고 진다면") {
            val dealer = GameParticipant.Dealer(
                "dealer",
                listOf(
                    Card(Card.Symbol.DIAMOND, Card.Number.JACK),
                    Card(Card.Symbol.DIAMOND, Card.Number.NINE)
                )
            )
            val matchResults = GameScoreCalculator(GameParticipants(players, dealer)).getMatchResult()
            Then("딜러는 1승 1패를 한다.") {
                matchResults.dealer.matchResults shouldBe listOf(MatchResult.LOSS, MatchResult.WIN)
            }
            Then("두 명의 플레이어는 각각 승과 패를 한다.") {
                matchResults.players shouldBe listOf(
                    GameParticipantResult.Player("winner", MatchResult.WIN),
                    GameParticipantResult.Player("loser", MatchResult.LOSS)
                )
            }
        }
        When("딜러가 버스트한다면") {
            val dealer = GameParticipant.Dealer(
                "dealer",
                listOf(
                    Card(Card.Symbol.DIAMOND, Card.Number.JACK),
                    Card(Card.Symbol.DIAMOND, Card.Number.KING),
                    Card(Card.Symbol.DIAMOND, Card.Number.TWO)
                )
            )
            val matchResults = GameScoreCalculator(GameParticipants(players, dealer)).getMatchResult()
            Then("딜러는 모든 플레이어에게 패배한다.") {
                matchResults.dealer.matchResults shouldBe listOf(MatchResult.LOSS, MatchResult.LOSS)
            }
            Then("두 명의 플레이어는 소유한 카드와 상관없이 승리한다.") {
                matchResults.players shouldBe listOf(
                    GameParticipantResult.Player("winner", MatchResult.WIN),
                    GameParticipantResult.Player("loser", MatchResult.WIN)
                )
            }
        }
    }
})

package blackjack.model

import blackjack.model.participant.BlackjackDealer
import blackjack.model.participant.BlackjackPlayer
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@DisplayName("블랙잭 수익 심판")
class BlackjackRevenueJudgeTest : StringSpec({

    "딜러와 사용자들로 생성" {
        // given
        val cardDeck = CardDeck()
        val dealer = BlackjackDealer(cardDeck) { _ -> }
        val player1 = BlackjackPlayer(cardDeck, { _ -> 1000 }, PlayerName("name"), { _ -> }, { _ -> false })
        // when & then
        shouldNotThrowAny {
            BlackjackRevenueJudge(dealer, listOf(player1))
        }
    }

    "딜러와 참가자들의 모든 수익금은 0원이어야 함" {
        // given
        val cardDeck = CardDeck()
        val dealer = BlackjackDealer(cardDeck) { _ -> }
        val bettingMoney = 1000
        val player1 = BlackjackPlayer(cardDeck, { _ -> bettingMoney }, PlayerName("name"), { _ -> }, { _ -> false })
        val player2 = BlackjackPlayer(cardDeck, { _ -> bettingMoney }, PlayerName("name"), { _ -> }, { _ -> false })
        val revenueJudge = BlackjackRevenueJudge(dealer, listOf(player1, player2))
        // when
        val totalRevenue =
            revenueJudge.dealerRevenue + revenueJudge.playerResults.map { it.revenue }.reduce(Money::plus)
        // then
        totalRevenue shouldBe Money.ZERO
    }
})

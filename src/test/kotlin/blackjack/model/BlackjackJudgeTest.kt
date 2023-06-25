package blackjack.model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

@DisplayName("블랙잭 심판")
class BlackjackJudgeTest : StringSpec({

    "딜러와 사용자들로 생성" {
        // given
        val cardDeck = CardDeck()
        val dealer = BlackjackDealer(cardDeck) { _ -> }
        val player1 = BlackjackPlayer(PlayerName("name"), cardDeck, { _ -> }, { _ -> false })
        // when & then
        shouldNotThrowAny {
            BlackjackJudge(dealer, listOf(player1))
        }
    }

    "딜러 이긴 횟수 패배 횟수는 플레이어들 보다 작거나 같음" {
        // given
        val cardDeck = CardDeck()
        val dealer = BlackjackDealer(cardDeck) { _ -> }
        val player1 = BlackjackPlayer(PlayerName("name"), cardDeck, { _ -> }, { _ -> false })
        val blackjackJudge = BlackjackJudge(dealer, listOf(player1))
        // when & then
        assertSoftly {
            blackjackJudge.dealerWinCount shouldBeLessThanOrEqual 1
            blackjackJudge.dealerLoseCount shouldBeLessThanOrEqual 1
        }
    }
})

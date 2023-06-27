package blackjack

import blackjack.domain.CardType.CLUB
import blackjack.domain.CardType.DIAMOND
import blackjack.domain.CardType.HEART
import blackjack.domain.CardType.SPADE
import blackjack.domain.CardValue.ACE
import blackjack.domain.CardValue.EIGHT
import blackjack.domain.CardValue.FIVE
import blackjack.domain.CardValue.TEN
import blackjack.domain.CardValue.THREE
import blackjack.domain.PlayerCards
import blackjack.domain.PlayingCard.Companion.of
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class BlackJackPointRuleTest : FreeSpec({

    "카드 포인트의 총 합을 계산할 수 있다." {
        val playerCards = PlayerCards()
        playerCards.add(
            THREE of HEART,
            EIGHT of SPADE,
            FIVE of CLUB,
            FIVE of DIAMOND
        )

        playerCards.point() shouldBe 21
    }

    "ACE 가 포함된 경우 총 합이 21이 초과되지 않도록 1 or 11 로 처리 될 수 있다." - {
        withData(
            mapOf(
                "블랙잭 with ace by 11" to Pair(arrayOf(TEN of HEART, ACE of HEART), 21),
                "블랙잭 with ace by 1" to Pair(arrayOf(TEN of HEART, ACE of HEART, TEN of CLUB), 21),
                "`stay or hit` with ace by 1" to Pair(arrayOf(FIVE of HEART, ACE of HEART, TEN of CLUB), 16),
                "ace 4장" to Pair(arrayOf(ACE of HEART, ACE of DIAMOND, ACE of CLUB, ACE of SPADE), 14),
            )
        ) { (cards, expectedPoint) ->
            val playerCards = PlayerCards()
            playerCards.add(*cards)

            playerCards.point() shouldBe expectedPoint
        }
    }
})

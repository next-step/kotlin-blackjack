package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.BlackjackUser
import study.blackjack.model.Card
import study.blackjack.model.CardRank
import study.blackjack.model.Cards
import study.blackjack.model.Match
import study.blackjack.model.Suit

/**
 * @author 이상준
 */
class BlackjackGameTest : StringSpec({
    "플레이어 승 테스트" {
        val playerCards =
            Cards(
                listOf(
                    Card(Suit.DIAMOND, CardRank.NINE),
                    Card(Suit.DIAMOND, CardRank.TEN),
                ),
            )
        val dealerCards =
            Cards(
                listOf(
                    Card(Suit.DIAMOND, CardRank.EIGHT),
                    Card(Suit.DIAMOND, CardRank.KING),
                ),
            )

        val player = BlackjackUser("player", cards = playerCards)
        val dealer = BlackjackUser("dealer", cards = dealerCards)
        player.match(dealer)

        player.result() shouldBe Match.WIN
    }
    "플레이어 패배 테스트" {
        val playerCards =
            Cards(
                listOf(
                    Card(Suit.DIAMOND, CardRank.SIX),
                    Card(Suit.DIAMOND, CardRank.TEN),
                ),
            )
        val dealerCards =
            Cards(
                listOf(
                    Card(Suit.DIAMOND, CardRank.EIGHT),
                    Card(Suit.DIAMOND, CardRank.TEN),
                ),
            )

        val player = BlackjackUser("player", cards = playerCards)
        val dealer = BlackjackUser("dealer", cards = dealerCards)
        player.match(dealer)

        player.result() shouldBe Match.LOSE
    }
})

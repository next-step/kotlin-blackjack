package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({

    "포커 게임을 시작하면,  딜러와 플레이어들에게 2장의 카드가 지급되고 총카드에서 제외된다" {
        // given
        val player1 = Player("1번주자")
        val player2 = Player("2번주자")
        val dealer = Dealer()

        // when
        val blackJackGame = BlackJackGame(dealer, Players(listOf(player1, player2)))

        // then
        blackJackGame.getPlayers().forEach {
            it.cards.count() shouldBe 2
        }
        dealer.cardCount() shouldBe 46
    }
})

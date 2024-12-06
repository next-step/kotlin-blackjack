package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec ({

    "블랙잭은 게임 플레이어와 카드 덱 조합으로 진행된다." {
        // Arrange:
        val players = Players(listOf(Player(name = PlayerName("player1"))))
        val deck = Deck()

        // Act:
        val blackJackGame = BlackJackGame(players, deck)

        // Assert:
        blackJackGame shouldBe BlackJackGame(players, deck)
    }

    "블랙잭 게임은 시작과 동시에 게임 플레이어에게 카드를 2장씩 나눠준다." {
        // Arrange:
        val players = Players(listOf(Player(name = PlayerName("player1"))))
        val deck = Deck()
        val blackJackGame = BlackJackGame(players, deck)

        // Act:
        val result = blackJackGame.start()

        // Assert:
        result.gamePlayers.player[0].cards.cards.size shouldBe 2
        deck.cards.size shouldBe 50
    }
})
package blackjack.flow

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Participants
import blackjack.participant.Player
import blackjack.participant.PlayerName
import blackjack.participant.Players
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({

    "블랙잭은 게임 플레이어와 카드 덱 조합으로 진행된다." {
        // Arrange:
        val dealer = Dealer()
        val players = Players(listOf(Player(name = PlayerName("player1"))))
        val deck = Deck()

        // Act:
        val blackJackGame = BlackJackGame(Participants(dealer, players), TurnMachine(deck))

        // Assert:
        blackJackGame shouldBe BlackJackGame(Participants(dealer, players), TurnMachine(deck))
    }

    "블랙잭 게임은 시작과 동시에 딜러와 게임 플레이어에게 카드를 2장씩 나눠준다." {
        // Arrange:
        val dealer = Dealer()
        val playerNames = Players.of(listOf("player1"))
        val deck = Deck()

        // Act:
        val result = BlackJackGame.start(dealer, playerNames, deck)

        // Assert:
        result.participants.gamePlayers.players[0].cardHolder.cards.cards.size shouldBe 2
        deck.cards.size shouldBe 48
    }
})

package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Deck
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.RandomCardStrategy
import blackjack.domain.Shape
import blackjack.view.InputView
import blackjack.view.OutputView
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어는 이름을 가진다" {
        val player = Player("Park", Cards())
        player.name shouldBe "Park"
    }

    "플레이어는 게임 시작 시 2장의 카드를 지급 받는다" {
        val game = BlackjackGame(InputView(), OutputView(), Deck(RandomCardStrategy()))
        val players = listOf("Park", "Kim")
        game.transformToPlayers(players)
        game.getPlayers().forEach { player ->
            player.cards.get().size shouldBe 2
        }
    }

    "플레이어는 가진 카드의 합을 결과로 반환할 수 있다" {
        val player1Cards = Cards().apply {
            add(Card(Denomination.JACK, Shape.HEARTS))
            add(Card(Denomination.TWO, Shape.CLUBS))
            add(Card(Denomination.FOUR, Shape.SPADED))
        }
        val player1 = Player("Park", player1Cards)

        player1.getTotalScore() shouldBe 16
    }
})

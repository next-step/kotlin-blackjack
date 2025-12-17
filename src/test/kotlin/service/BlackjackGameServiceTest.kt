package service

import domain.Dealer
import domain.Player
import domain.Players
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BlackjackGameServiceTest : FreeSpec({

    "딜러와 참가자에게 초기 카드 2장씩 분배" {
        val gameService = BlackjackGameService()
        val dealer = Dealer()
        val players = Players(listOf(Player("pobi"), Player("jason")))

        gameService.drawInitialCards(dealer, players)

        dealer.cards.cards().size shouldBe 2
        players.players[0].cards.cards().size shouldBe 2
        players.players[1].cards.cards().size shouldBe 2
    }

    "참가자에게 카드 한 장 추가 분배" {
        val gameService = BlackjackGameService()
        val player = Player("pobi")
        val initialCardCount = player.cards.cards().size

        gameService.drawCards(player)

        player.cards.cards().size shouldBe initialCardCount + 1
    }

    "딜러에게 카드 한 장 추가 분배" {
        val gameService = BlackjackGameService()
        val dealer = Dealer()
        val initialCardCount = dealer.cards.cards().size

        gameService.drawCards(dealer)

        dealer.cards.cards().size shouldBe initialCardCount + 1
    }

    "카드 분배 시 중복되지 않는 카드 확인" {
        val gameService = BlackjackGameService()
        val dealer = Dealer()
        val players = Players(listOf(Player("pobi"), Player("jason")))

        gameService.drawInitialCards(dealer, players)

        val allCards = mutableListOf<domain.Card>()
        allCards.addAll(dealer.cards.cards())
        allCards.addAll(players.players[0].cards.cards())
        allCards.addAll(players.players[1].cards.cards())

        allCards.toSet().size shouldBe allCards.size
    }
})

package service

import domain.Card
import domain.Cards
import domain.Dealer
import domain.Player
import domain.Players
import domain.Rank
import domain.Suit
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BlackjackGameServiceTest : FreeSpec({

    "딜러와 참가자에게 초기 카드 2장씩 분배" {
        val gameService = BlackjackGameService()
        val dealer = Dealer()
        val players = Players(listOf(Player("pobi", 1000), Player("jason", 1000)))

        gameService.drawInitialCards(dealer, players)

        dealer.cards.cards().size shouldBe 2
        players.players[0].cards.cards().size shouldBe 2
        players.players[1].cards.cards().size shouldBe 2
    }

    "참가자에게 카드 한 장 추가 분배" {
        val gameService = BlackjackGameService()
        val player = Player("pobi", 1000, Cards(mutableListOf(Card(Suit.SPADE, Rank.TEN), Card(Suit.DIAMOND, Rank.TEN))))
        val initialCardCount = player.cards.cards().size

        gameService.drawPlayerCards(player, { true }) {}

        player.cards.cards().size shouldBe initialCardCount + 1
    }

    "참가자에게 카드 추가 분배하지 않음" {
        val gameService = BlackjackGameService()
        val player = Player("pobi", 1000)
        val initialCardCount = player.cards.cards().size

        gameService.drawPlayerCards(player, { false }) {}

        player.cards.cards().size shouldBe initialCardCount
    }

    "딜러에게 카드 한 장 추가 분배" {
        val gameService = BlackjackGameService()
        val dealer = Dealer()
        val initialCardCount = dealer.cards.cards().size

        gameService.drawDealerCards(dealer) {}

        dealer.cards.cards().size shouldBe initialCardCount + 1
    }

    "카드 분배 시 중복되지 않는 카드 확인" {
        val gameService = BlackjackGameService()
        val dealer = Dealer()
        val players = Players(listOf(Player("pobi", 1000), Player("jason", 1000)))

        gameService.drawInitialCards(dealer, players)

        val allCards = mutableListOf<Card>()
        allCards.addAll(dealer.cards.cards())
        allCards.addAll(players.players[0].cards.cards())
        allCards.addAll(players.players[1].cards.cards())

        allCards.toSet().size shouldBe allCards.size
    }
})

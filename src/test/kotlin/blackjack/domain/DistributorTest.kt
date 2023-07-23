package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DistributorTest : StringSpec({
    "딜러와 플레이어들에 카드가 2장씩 분배 되었는지" {
        val dealer = Dealer("dealer", Cards(mutableListOf()))
        val player1 = Player("player1", Cards(mutableListOf()))
        val player2 = Player("player2", Cards(mutableListOf()))
        val players = Players(listOf(player1, player2))

        val distributor = Distributor(CardDeck())
        distributor.dealOutCards(dealer, players)

        dealer.cards.size shouldBe 2
        players.forEach { player -> player.cards.size shouldBe 2 }
    }
})

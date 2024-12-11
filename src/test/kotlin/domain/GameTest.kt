package domain

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.HitCommand
import blackjack.domain.Player
import blackjack.domain.Players
import fixture.CardListFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameTest : DescribeSpec({
    lateinit var players: Players
    lateinit var dealer: Dealer
    lateinit var sut: Game

    beforeTest {
        players = Players(listOf(Player("pobi"), Player("jason")))
        dealer = Dealer(Deck(CardListFixture.simpleCardList()))
        sut = Game(dealer, players)
    }

    describe("hit or not test") {
        it("플레이어의 커멘드가 y이면 카드를 받는다") {
            val player = players.allPlayers()[0]
            sut.processPlayerTurn(player, HitCommand.Y)

            player.ownedCards.size shouldBe 1
        }
    }
})

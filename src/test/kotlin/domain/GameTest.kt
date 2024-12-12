package domain

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
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

    describe("init test") {
        it("게임을 시작하면 각 플레이어들에게 카드를 2장씩 나누어준다.") {
            players.allPlayers()[0].ownedCards.size shouldBe 2
            players.allPlayers()[1].ownedCards.size shouldBe 2
        }
    }
})

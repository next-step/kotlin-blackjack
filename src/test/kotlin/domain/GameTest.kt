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

    describe("init test") {
        it("게임을 시작하면 각 플레이어들에게 카드를 2장씩 나누어준다.") {
            players.allPlayers()[0].ownedCards.size shouldBe 2
            players.allPlayers()[1].ownedCards.size shouldBe 2
        }
    }

    describe("processPlayerTurn Test") {
        lateinit var player: Player
        beforeTest { player = Player("pobi") }

        context("`command`가 `HIT`인 경우") {
            it("카드를 한장 받는다.") {
                sut.processPlayerTurn(player, HitCommand.HIT)
                player.ownedCards.size shouldBe 1
            }
        }

        context("`command`가 `STAY`인 경우") {
            it("카드를 받지 않는다.") {
                sut.processPlayerTurn(player, HitCommand.STAY)
                player.ownedCards.size shouldBe 0
            }

            it("턴을 종료한다.") {
            }
        }
    }

    describe("Player 상태를 기준으로 완료 여부를 결정한다.") {
        context("Player가 stay를 원하는 경우") {
            it("should be false") {
                val pobi = players.allPlayers()[0]
                val actual = sut.isPlayerDone(pobi)
                actual shouldBe false
            }
        }

        context("Player가 hit을 원하는 경우") {
            it("should be true") {
                val pobi = players.allPlayers()[0]
                val actual = sut.isPlayerDone(pobi)
                actual shouldBe true
            }
        }
    }
})

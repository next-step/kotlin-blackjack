package blackjack.service

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.dto.PlayerInfo
import blackjack.enums.Rank
import blackjack.enums.Symbol
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackServiceTest {

    private lateinit var player1: Player
    private lateinit var player2: Player

    @BeforeEach
    fun setup() {
        player1 = Player(
            name = "",
            cards = Cards(
                cards = listOf(
                    Card(rank = Rank.ACE, symbol = Symbol.SPADES),
                    Card(rank = Rank.JACK, symbol = Symbol.SPADES)
                )
            ),
            betAmount = 10000.0
        )

        player2 = Player(
            name = "",
            cards = Cards(
                cards = listOf(
                    Card(rank = Rank.SIX, symbol = Symbol.SPADES),
                    Card(rank = Rank.JACK, symbol = Symbol.SPADES)
                )
            ),
            betAmount = 10000.0
        )
    }

    @Test
    fun `블랙잭 게임 초기 시작 시 플레이어 당 2장 씩 딜러도 2장 카드를 가진다`() {
        val players = listOf(PlayerInfo("test1", 10000.0), PlayerInfo("test2", 10000.0))
        val blackjackGame = BlackjackService().initBlackjackGame(players)

        val player1CardCount = blackjackGame.players[0].cards.cards.size
        val player2CardCount = blackjackGame.players[1].cards.cards.size
        val dealerCardCount = blackjackGame.dealer.cards.cards.size
        player1CardCount shouldBe BASIC_CARD_COUNT
        player2CardCount shouldBe BASIC_CARD_COUNT
        dealerCardCount shouldBe BASIC_CARD_COUNT

        val expected = TOTAL_CARD_COUNT - (player1CardCount + player2CardCount + dealerCardCount)
        blackjackGame.dealer.deck.cardCount shouldBe expected
    }

    @Test
    fun `처음 두 장의 카드 합이 21일 경우 블랙잭이 되어 게임 경기가 종료되며 블랙잭 플레이어의 베팅 금액의 150%를 받는다`() {

        player1.checkBlackjack()
        player2.checkBlackjack()

        val blackjackGame = BlackjackGame(
            players = listOf(player1, player2),
            dealer = Dealer(
                name = "",
                cards = Cards(
                    cards = listOf(
                        Card(rank = Rank.SIX, symbol = Symbol.SPADES),
                        Card(rank = Rank.TWO, symbol = Symbol.SPADES)
                    )
                ),
                deck = Deck()
            )
        )
        val actual = BlackjackService().checkAllPlayersBlackjack(blackjackGame)
        actual shouldBe true
        player1.betAmount shouldBe 15_000.0
    }

    @Test
    fun `처음 두 장의 카드 합이 딜러와 플레이어가 모두 동시에 21인 경우 블랙잭이 되어 게임 경기가 종료되며 블랙잭 플레이어만 배팅 금액을 돌려 받는다`() {

        player1.checkBlackjack()
        player2.checkBlackjack()

        val blackjackGame = BlackjackGame(
            players = listOf(player1, player2),
            dealer = Dealer(
                name = "",
                cards = Cards(
                    cards = listOf(
                        Card(rank = Rank.ACE, symbol = Symbol.HEARTS),
                        Card(rank = Rank.KING, symbol = Symbol.DIAMONDS)
                    )
                ),
                deck = Deck()
            )
        )
        blackjackGame.dealer.checkBlackjack()

        val actual = BlackjackService().checkAllPlayersBlackjack(blackjackGame)
        actual shouldBe true
        player1.betAmount shouldBe 10_000.0
        player2.betAmount shouldBe 0
    }

    companion object {
        const val TOTAL_CARD_COUNT = 52
        const val BASIC_CARD_COUNT = 2
    }
}

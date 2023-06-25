package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.DealerResult
import blackjack.domain.card.Card
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.player.GamePlayers
import blackjack.domain.player.GameResult
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `게임에 참여할 Player와 CardDeck이 필요하다`() {
        val player1 = Player()
        val player2 = Player()
        val gamePlayers = GamePlayers.from(player1, player2)
        val blackjackGame = BlackjackGame(gamePlayers)

        blackjackGame.gamePlayers shouldBe gamePlayers
        blackjackGame.deck.shouldNotBeNull()
    }

    @Test
    fun `최초 카드분배시 Player에게 2장의 카드를 나눠준다`() {
        val player = Player()
        val blackjackGame = BlackjackGame(GamePlayers.from(player))

        blackjackGame.initPlayers()

        player.cards.getValue().size shouldBe 2
    }

    @Test
    fun `최초 카드분배 이후에는 Player에게 카드를 1장씩 나눠준다`() {
        val player = Player()
        val blackjackGame = BlackjackGame(GamePlayers.from(player))

        blackjackGame.dealCard(player)

        player.cards.getValue().size shouldBe 1
    }

    @Test
    fun `ACE는 1 또는 11 score로 계산될 수 있어서 21을 넘지 않는 가장 가까운 수로 계산된다`() {
        // 7, 17
        val player = Player()
        val cards = Cards(
            mutableListOf(
                Card(Denomination.ACE, CardType.CLUBS),
                Card(Denomination.SIX, CardType.DIAMONDS),
            ),
        )

        player.initCards(cards)

        // 17, 27
        player.receiveCard(Card(Denomination.JACK, CardType.HEARTS))

        player.cards.getOptimizedScore() shouldBe 17
    }

    @Test
    fun `딜러가 3승을 하는 게임 결과`() {
        val gamePlayers = GamePlayers.from(Player(Name("hue")), Player(Name("jason")), Player(Name("Flamme")))
        val initCards = listOf(
            Cards.from(Card(Denomination.TWO, CardType.HEARTS), Card(Denomination.JACK, CardType.CLUBS)), // 12
            Cards.from(Card(Denomination.THREE, CardType.HEARTS), Card(Denomination.JACK, CardType.DIAMONDS)), // 13
            Cards.from(Card(Denomination.FOUR, CardType.HEARTS), Card(Denomination.JACK, CardType.SPADES)), // 14
            Cards.from(Card(Denomination.ACE, CardType.HEARTS), Card(Denomination.JACK, CardType.HEARTS)), // dealer: 20
        )

        gamePlayers.players.plus(gamePlayers.dealer).forEachIndexed { index, gamePlayer ->
            gamePlayer.initCards(initCards[index])
        }

        val gameResult = gamePlayers.getGameResult()

        with(gameResult.dealer) {
            dealer shouldBe gamePlayers.dealer
            result.size shouldBe 3
            result shouldBe listOf(
                DealerResult.DealerGameResultPair(GameResult.WIN, 3),
                DealerResult.DealerGameResultPair(GameResult.LOOSE, 0),
                DealerResult.DealerGameResultPair(GameResult.TIE, 0),
            )
        }

        with(gameResult.players) {
            this[0].result shouldBe GameResult.LOOSE
            this[1].result shouldBe GameResult.LOOSE
            this[2].result shouldBe GameResult.LOOSE
        }
    }

    @Test
    fun `딜러가 1승 1패 1무를 하는 게임 결과`() {
        val gamePlayers = GamePlayers.from(Player(Name("hue")), Player(Name("jason")), Player(Name("Flamme")))
        val initCards = listOf(
            Cards.from(Card(Denomination.ACE, CardType.HEARTS), Card(Denomination.JACK, CardType.CLUBS)), // 21
            Cards.from(Card(Denomination.QUEEN, CardType.HEARTS), Card(Denomination.JACK, CardType.DIAMONDS)), // 20
            Cards.from(Card(Denomination.FOUR, CardType.HEARTS), Card(Denomination.JACK, CardType.SPADES)), // 14
            Cards.from(
                Card(Denomination.KING, CardType.HEARTS),
                Card(Denomination.JACK, CardType.HEARTS),
            ), // dealer: 20
        )

        gamePlayers.players.plus(gamePlayers.dealer).forEachIndexed { index, gamePlayer ->
            gamePlayer.initCards(initCards[index])
        }

        val gameResult = gamePlayers.getGameResult()

        with(gameResult.dealer) {
            dealer shouldBe gamePlayers.dealer
            result.size shouldBe 3
            result shouldBe listOf(
                DealerResult.DealerGameResultPair(GameResult.WIN, 1),
                DealerResult.DealerGameResultPair(GameResult.LOOSE, 1),
                DealerResult.DealerGameResultPair(GameResult.TIE, 1),
            )
        }

        with(gameResult.players) {
            this[0].result shouldBe GameResult.WIN
            this[1].result shouldBe GameResult.TIE
            this[2].result shouldBe GameResult.LOOSE
        }
    }

    @Test
    fun `딜러가 3패를 하는 게임 결과`() {
        val gamePlayers = GamePlayers.from(Player(Name("hue")), Player(Name("jason")), Player(Name("Flamme")))
        val initCards = listOf(
            Cards.from(Card(Denomination.ACE, CardType.HEARTS), Card(Denomination.JACK, CardType.CLUBS)), // 21
            Cards.from(Card(Denomination.QUEEN, CardType.HEARTS), Card(Denomination.JACK, CardType.DIAMONDS)), // 20
            Cards.from(Card(Denomination.FOUR, CardType.HEARTS), Card(Denomination.JACK, CardType.SPADES)), // 14
            Cards.from(
                Card(Denomination.JACK, CardType.HEARTS),
                Card(Denomination.QUEEN, CardType.HEARTS),
                Card(Denomination.FOUR, CardType.SPADES),
            ), // dealer: 24
        )

        gamePlayers.players.plus(gamePlayers.dealer).forEachIndexed { index, gamePlayer ->
            gamePlayer.initCards(initCards[index])
        }

        val gameResult = gamePlayers.getGameResult()

        with(gameResult.dealer) {
            dealer shouldBe gamePlayers.dealer
            result.size shouldBe 3
            result shouldBe listOf(
                DealerResult.DealerGameResultPair(GameResult.WIN, 0),
                DealerResult.DealerGameResultPair(GameResult.LOOSE, 3),
                DealerResult.DealerGameResultPair(GameResult.TIE, 0),
            )
        }

        with(gameResult.players) {
            this[0].result shouldBe GameResult.WIN
            this[1].result shouldBe GameResult.WIN
            this[2].result shouldBe GameResult.WIN
        }
    }
}

package blackjack

import blackjack.domain.DealerResult
import blackjack.domain.card.Card
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.player.GamePlayers
import blackjack.domain.player.GameResult
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerBetAmount
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackGameResultTest {

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

        val gameResult = gamePlayers.finish()

        with(gameResult.dealer) {
            dealer shouldBe gamePlayers.dealer
            result.size shouldBe 3
            result shouldBe listOf(
                DealerResult.DealerGameResultPair(GameResult.WIN, 3),
                DealerResult.DealerGameResultPair(GameResult.LOOSE, 0),
                DealerResult.DealerGameResultPair(GameResult.TIE, 0),
            )
        }

        with(gameResult.players.results) {
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

        val gameResult = gamePlayers.finish()

        with(gameResult.dealer) {
            dealer shouldBe gamePlayers.dealer
            result.size shouldBe 3
            result shouldBe listOf(
                DealerResult.DealerGameResultPair(GameResult.WIN, 1),
                DealerResult.DealerGameResultPair(GameResult.LOOSE, 1),
                DealerResult.DealerGameResultPair(GameResult.TIE, 1),
            )
        }

        with(gameResult.players.results) {
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

        val gameResult = gamePlayers.finish()

        with(gameResult.dealer) {
            dealer shouldBe gamePlayers.dealer
            result.size shouldBe 3
            result shouldBe listOf(
                DealerResult.DealerGameResultPair(GameResult.WIN, 0),
                DealerResult.DealerGameResultPair(GameResult.LOOSE, 3),
                DealerResult.DealerGameResultPair(GameResult.TIE, 0),
            )
        }

        with(gameResult.players.results) {
            this[0].result shouldBe GameResult.WIN
            this[1].result shouldBe GameResult.WIN
            this[2].result shouldBe GameResult.WIN
        }
    }

    @Test
    fun `플레이어가 Blackjack 이면서 승리하면 딜러에게 기존 베팅금액에 절반만큼 더 받는다`() {
        val betAmount = PlayerBetAmount(10000L)
        val gamePlayers = GamePlayers.from(Player(Name("hue"), betAmount))
        val initCards = listOf(
            Cards.from(Card(Denomination.ACE, CardType.HEARTS), Card(Denomination.JACK, CardType.CLUBS)), // 21
            Cards.from(
                Card(Denomination.JACK, CardType.HEARTS),
                Card(Denomination.QUEEN, CardType.HEARTS),
            ), // dealer: 20
        )

        gamePlayers.players.plus(gamePlayers.dealer).forEachIndexed { index, gamePlayer ->
            gamePlayer.initCards(initCards[index])
        }

        val gameResult = gamePlayers.finish()

        with(gameResult) {
            // 딜러 패배 확인
            dealer.result.first { it.gameResult == GameResult.LOOSE } shouldBe DealerResult.DealerGameResultPair(
                GameResult.LOOSE,
                1,
            )

            // 플레이어 승리 확인
            players.results[0].result shouldBe GameResult.WIN

            // 베팅금액 확인
            dealer.dealer.revenue.value shouldBe -15000L
            players.results[0].player.betAmount.revenue shouldBe 15000L
        }
    }

    @Test
    fun `딜러와 플레이어가 모두 Blackjack 이면, 플레이어는 베팅 금액을 돌려받는다`() {
        val betAmount = PlayerBetAmount(10000L)
        val gamePlayers = GamePlayers.from(Player(Name("hue"), betAmount))
        val initCards = listOf(
            Cards.from(Card(Denomination.ACE, CardType.HEARTS), Card(Denomination.JACK, CardType.CLUBS)), // 21
            Cards.from(
                Card(Denomination.ACE, CardType.DIAMONDS),
                Card(Denomination.QUEEN, CardType.HEARTS),
            ), // dealer: 21
        )

        gamePlayers.players.plus(gamePlayers.dealer).forEachIndexed { index, gamePlayer ->
            gamePlayer.initCards(initCards[index])
        }

        val gameResult = gamePlayers.finish()

        with(gameResult) {
            // 딜러 무승부 확인
            dealer.result.first { it.gameResult == GameResult.TIE } shouldBe DealerResult.DealerGameResultPair(
                GameResult.TIE,
                1,
            )

            // 플레이어 무승부 확인
            players.results[0].result shouldBe GameResult.TIE

            // 베팅금액 확인
            dealer.dealer.revenue.value shouldBe 0L
            players.results[0].player.betAmount.revenue shouldBe 0L
        }
    }

    @Test
    fun `플레이어가 21 score를 초과하여 BUST 상태가 되면, 플레이어는 베팅 금액을 잃는다`() {
        val betAmount = PlayerBetAmount(10000L)
        val gamePlayers = GamePlayers.from(Player(Name("hue"), betAmount))
        val initCards = listOf(
            Cards.from(
                Card(Denomination.KING, CardType.HEARTS),
                Card(Denomination.JACK, CardType.CLUBS),
                Card(Denomination.TWO, CardType.CLUBS),
            ), // 22
            Cards.from(
                Card(Denomination.FOUR, CardType.DIAMONDS),
                Card(Denomination.QUEEN, CardType.HEARTS),
            ), // dealer: 14
        )

        gamePlayers.players.plus(gamePlayers.dealer).forEachIndexed { index, gamePlayer ->
            gamePlayer.initCards(initCards[index])
        }

        val gameResult = gamePlayers.finish()

        with(gameResult) {
            // 딜러 승리 확인
            dealer.result.first { it.gameResult == GameResult.WIN } shouldBe DealerResult.DealerGameResultPair(
                GameResult.WIN,
                1,
            )

            // 플레이어 패배 확인
            players.results[0].result shouldBe GameResult.LOOSE

            // 베팅금액 확인
            dealer.dealer.revenue.value shouldBe 10000L
            players.results[0].player.betAmount.revenue shouldBe -10000L
        }
    }
}

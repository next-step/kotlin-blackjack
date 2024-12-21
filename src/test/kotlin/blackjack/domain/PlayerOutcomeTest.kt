package blackjack.domain

import blackjack.support.Fixtures.createBlackjackDealer
import blackjack.support.Fixtures.createBlackjackPlayer
import blackjack.support.Fixtures.createBustedDealer
import blackjack.support.Fixtures.createBustedPlayer
import blackjack.support.Fixtures.createDealer
import blackjack.support.Fixtures.createPlayer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class PlayerOutcomeTest {
    @Test
    fun `플레이어가 버스트한 경우 패배한다`() {
        val bustedPlayer = createBustedPlayer("jack")
        val dealer = createDealer(StubDeck.from(Rank.SIX, Rank.SEVEN))

        val outcome = PlayerOutcome.of(bustedPlayer, dealer)

        outcome shouldBe PlayerOutcome.LOSE
    }

    @Test
    fun `딜러가 버스트한 경우 버스트하지 않은 플레이어는 승리한다`() {
        val player = createPlayer(StubDeck.from(Rank.JACK, Rank.TEN))
        val bustedDealer = createBustedDealer()

        val outcome = PlayerOutcome.of(player, bustedDealer)

        outcome shouldBe PlayerOutcome.WIN
    }

    @Test
    fun `딜러와 플레이어가 모두 버스트한 경우 플레이어는 패배한다`() {
        val bustedPlayer = createBustedPlayer()
        val bustedDealer = createBustedDealer()

        val outcome = PlayerOutcome.of(bustedPlayer, bustedDealer)

        outcome shouldBe PlayerOutcome.LOSE
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러가 블랙잭이 아닌 경우 승리한다`() {
        val blackjackPlayer = createBlackjackPlayer()
        val dealer = createDealer()

        val outcome = PlayerOutcome.of(blackjackPlayer, dealer)

        outcome shouldBe PlayerOutcome.WIN
    }

    @Test
    fun `플레이러와 딜러 모두 블랙잭이면 무승부다`() {
        val blackjackPlayer = createBlackjackPlayer()
        val blackjackDealer = createBlackjackDealer()

        val outcome = PlayerOutcome.of(blackjackPlayer, blackjackDealer)

        outcome shouldBe PlayerOutcome.DRAW
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 높으면 승리한다`() {
        val player = createPlayer(StubDeck.from(Rank.JACK, Rank.TEN))
        val dealer = createDealer(StubDeck.from(Rank.TWO, Rank.THREE))

        val outcome = PlayerOutcome.of(player, dealer)

        outcome shouldBe PlayerOutcome.WIN
    }

    @Test
    fun `플레이어 점수와 딜러의 점수가 같은 경우 무승부다`() {
        val player = createPlayer(StubDeck.from(Rank.FIVE, Rank.TEN))
        val dealer = createDealer(StubDeck.from(Rank.SEVEN, Rank.EIGHT))

        val outcome = PlayerOutcome.of(player, dealer)

        outcome shouldBe PlayerOutcome.DRAW
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 낮으면 패배한다`() {
        val player = createPlayer(StubDeck.from(Rank.TWO, Rank.THREE))
        val dealer = createDealer(StubDeck.from(Rank.FOUR, Rank.FIVE))

        val outcome = PlayerOutcome.of(player, dealer)

        outcome shouldBe PlayerOutcome.LOSE
    }
}

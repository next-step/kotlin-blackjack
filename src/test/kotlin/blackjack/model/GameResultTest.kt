package blackjack.model

import blackjack.domain.Cards
import blackjack.domain.FakeDealer
import blackjack.domain.FakeGamePlay
import blackjack.domain.FakePlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameResultTest {
    @Test
    fun `딜러와 플레이어가 블랙잭을 완성하였을 시 플레이어 이븐머니`() {
        // given
        val fakeDealer = FakeDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayer = FakePlayer(play = FakeGamePlay(_blackjack = true))

        // when
        val result = GameResult.of(fakeDealer, fakePlayer)

        // then
        assertThat(result).isEqualTo(GameResult.EVEN_MONEY)
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 stay 상태이면 플레이어 패`() {
        // given
        val fakeDealer = FakeDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayer = FakePlayer(play = FakeGamePlay(_stay = true))

        // when
        val result = GameResult.of(fakeDealer, fakePlayer)

        // then
        assertThat(result).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러 블랙잭 완성, 플레이어가 Bust 상태 플레이어 패`() {
        // given
        val fakeDealer = FakeDealer(play = FakeGamePlay(_blackjack = true))
        val fakePlayers = FakePlayer(play = FakeGamePlay(_bust = true))

        // when
        val result = GameResult.of(fakeDealer, fakePlayers)

        // then
        assertThat(result).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러 Stay 상태, 플레이어가 블랙잭 완성하였을 시 플레이어 블랙잭(승)`() {
        // given
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true))
        val fakePlayer = FakePlayer(play = FakeGamePlay(_blackjack = true))

        // when
        val result = GameResult.of(fakeDealer, fakePlayer)

        // then
        assertThat(result).isEqualTo(GameResult.BLACKJACK)
    }

    @Test
    fun `딜러 Bust 상태, 플레이어가 블랙잭 완성하였을 시 플레이어 블랙잭(승)`() {
        // given
        val fakeDealer = FakeDealer(play = FakeGamePlay(_bust = true))
        val fakePlayer = FakePlayer(play = FakeGamePlay(_blackjack = true))

        // when
        val result = GameResult.of(fakeDealer, fakePlayer)

        // then
        assertThat(result).isEqualTo(GameResult.BLACKJACK)
    }

    @Test
    fun `딜러 Stay 상태, 플레이어 Bust 상태 시 플레이어 패`() {
        // given
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true, score = 19))
        val fakePlayer = FakePlayer(play = FakeGamePlay(_bust = true, score = 24))

        // when
        val result = GameResult.of(fakeDealer, fakePlayer)

        // then
        assertThat(result).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러 Bust 상태, 플레이어 Bust 시 플레이어 패`() {
        // given
        val fakeDealer = FakeDealer(play = FakeGamePlay(_bust = true, score = 24))
        val fakePlayers = FakePlayer(play = FakeGamePlay(_bust = true, score = 24))

        // when
        val result = GameResult.of(fakeDealer, fakePlayers)

        // then
        assertThat(result).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러 Bust 상태, 플레이어 Stay 시 플레이어 승`() {
        // given
        val fakeDealer = FakeDealer(play = FakeGamePlay(_bust = true))
        val fakePlayer = FakePlayer(play = FakeGamePlay(_stay = true))

        // when
        val result = GameResult.of(fakeDealer, fakePlayer)

        // then
        assertThat(result).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 동일하면 PUSH(무)`() {
        // given
        val cards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.TEN, CardShape.DIAMOND),
            )
        )
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true, score = cards.sum()))
        val fakePlayer = FakePlayer(play = FakeGamePlay(_stay = true, score = cards.sum()))

        // when
        val result = GameResult.of(fakeDealer, fakePlayer)

        // then
        assertThat(result).isEqualTo(GameResult.PUSH)
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 딜러가 크면 플레이어 패`() {
        // given
        val dealerCards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.ACE, CardShape.DIAMOND),
            )
        )
        val playerCards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.TEN, CardShape.DIAMOND),
            )
        )
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true, score = dealerCards.sum()))
        val fakePlayer = FakePlayer(play = FakeGamePlay(_stay = true, score = playerCards.sum()))

        // when
        val result = GameResult.of(fakeDealer, fakePlayer)

        // then
        assertThat(result).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러, 플레이어 둘다 Stay 상태이고 카드의 총합이 플레이어가 크면 플레이어 승`() {
        // given
        val dealerCards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.TEN, CardShape.DIAMOND),
            )
        )
        val playerCards = Cards(
            listOf(
                Card(CardType.SEVEN, CardShape.DIAMOND),
                Card(CardType.ACE, CardShape.DIAMOND),
            )
        )
        val fakeDealer = FakeDealer(play = FakeGamePlay(_stay = true, score = dealerCards.sum()))
        val fakePlayer = FakePlayer(play = FakeGamePlay(_stay = true, score = playerCards.sum()))

        // when
        val result = GameResult.of(fakeDealer, fakePlayer)

        // then
        assertThat(result).isEqualTo(GameResult.WIN)
    }
}

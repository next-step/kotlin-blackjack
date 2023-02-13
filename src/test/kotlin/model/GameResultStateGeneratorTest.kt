package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameResultStateGeneratorTest {

    @Test
    fun `딜러가 버스트인 경우 모든 플레이어는 카드 값에 상관없이 승리한다`() {
        val dealer = Dealer()
        val players = Players()

        dealer.receiveCard(Card(CardNumber.KING, CardShape.SPADES))
        dealer.receiveCard(Card(CardNumber.FIVE, CardShape.HEARTS))
        dealer.receiveCard(Card(CardNumber.JACK, CardShape.DIAMONDS))

        players.create(Names("Tester1, Tester2"))

        players.values[0].receiveCard(Card(CardNumber.TWO, CardShape.SPADES))
        players.values[0].receiveCard(Card(CardNumber.KING, CardShape.HEARTS))

        players.values[1].receiveCard(Card(CardNumber.JACK, CardShape.SPADES))
        players.values[1].receiveCard(Card(CardNumber.KING, CardShape.HEARTS))
        players.values[1].receiveCard(Card(CardNumber.QUEEN, CardShape.HEARTS))

        GameResultStateGenerator().generate(dealer, players.values)

        assertThat(players.values[0].gameResultState).isEqualTo(GameResultState.WIN)
        assertThat(players.values[1].gameResultState).isEqualTo(GameResultState.WIN)
    }

    @Test
    fun `딜러가 버스트가 아니고 플레이어는 버스트인 경우 플레이어가 패배한다`() {
        val dealer = Dealer()
        val players = Players()

        dealer.receiveCard(Card(CardNumber.KING, CardShape.SPADES))
        dealer.receiveCard(Card(CardNumber.FIVE, CardShape.HEARTS))

        players.create(Names("Tester1"))

        players.values[0].receiveCard(Card(CardNumber.JACK, CardShape.SPADES))
        players.values[0].receiveCard(Card(CardNumber.KING, CardShape.HEARTS))
        players.values[0].receiveCard(Card(CardNumber.NINE, CardShape.HEARTS))

        GameResultStateGenerator().generate(dealer, players.values)

        assertThat(players.values[0].gameResultState).isEqualTo(GameResultState.LOSE)
    }

    @Test
    fun `딜러와 플레이어 모두 블랙잭일 경우 비긴다`() {
        val dealer = Dealer()
        val players = Players()

        dealer.receiveCard(Card(CardNumber.KING, CardShape.SPADES))
        dealer.receiveCard(Card(CardNumber.JACK, CardShape.HEARTS))
        dealer.receiveCard(Card(CardNumber.ACE, CardShape.DIAMONDS))

        players.create(Names("Tester1"))

        players.values[0].receiveCard(Card(CardNumber.JACK, CardShape.SPADES))
        players.values[0].receiveCard(Card(CardNumber.ACE, CardShape.HEARTS))

        GameResultStateGenerator().generate(dealer, players.values)

        assertThat(players.values[0].gameResultState).isEqualTo(GameResultState.DRAW)
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어는 블랙잭이 아닌 경우 플레이어가 패배한다`() {
        val dealer = Dealer()
        val players = Players()

        dealer.receiveCard(Card(CardNumber.KING, CardShape.SPADES))
        dealer.receiveCard(Card(CardNumber.JACK, CardShape.HEARTS))
        dealer.receiveCard(Card(CardNumber.ACE, CardShape.DIAMONDS))

        players.create(Names("Tester1"))

        players.values[0].receiveCard(Card(CardNumber.TWO, CardShape.SPADES))
        players.values[0].receiveCard(Card(CardNumber.ACE, CardShape.HEARTS))

        GameResultStateGenerator().generate(dealer, players.values)

        assertThat(players.values[0].gameResultState).isEqualTo(GameResultState.LOSE)
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러는 블랙잭이 아닌 경우 플레이어가 승리한다`() {
        val dealer = Dealer()
        val players = Players()

        dealer.receiveCard(Card(CardNumber.SIX, CardShape.SPADES))
        dealer.receiveCard(Card(CardNumber.ACE, CardShape.DIAMONDS))

        players.create(Names("Tester1"))

        players.values[0].receiveCard(Card(CardNumber.KING, CardShape.SPADES))
        players.values[0].receiveCard(Card(CardNumber.ACE, CardShape.HEARTS))

        GameResultStateGenerator().generate(dealer, players.values)

        assertThat(players.values[0].gameResultState).isEqualTo(GameResultState.WIN)
    }

    @Test
    fun `딜러와 플레이어 모두 블랙잭이 아닐 때 딜러의 값이 크면 플레이어가 패배한다`() {
        val dealer = Dealer()
        val players = Players()

        dealer.receiveCard(Card(CardNumber.KING, CardShape.SPADES))
        dealer.receiveCard(Card(CardNumber.NINE, CardShape.DIAMONDS))

        players.create(Names("Tester1"))

        players.values[0].receiveCard(Card(CardNumber.TWO, CardShape.SPADES))
        players.values[0].receiveCard(Card(CardNumber.FIVE, CardShape.HEARTS))

        GameResultStateGenerator().generate(dealer, players.values)

        assertThat(players.values[0].gameResultState).isEqualTo(GameResultState.LOSE)
    }

    @Test
    fun `딜러와 플레이어 모두 블랙잭이 아닐 때 플레이어의 값이 크면 플레이어가 승리한다`() {
        val dealer = Dealer()
        val players = Players()

        dealer.receiveCard(Card(CardNumber.THREE, CardShape.SPADES))
        dealer.receiveCard(Card(CardNumber.NINE, CardShape.DIAMONDS))

        players.create(Names("Tester1"))

        players.values[0].receiveCard(Card(CardNumber.QUEEN, CardShape.SPADES))
        players.values[0].receiveCard(Card(CardNumber.FIVE, CardShape.HEARTS))

        GameResultStateGenerator().generate(dealer, players.values)

        assertThat(players.values[0].gameResultState).isEqualTo(GameResultState.WIN)
        println("TEST ::: ${players.values[0].gameResultState}")
    }
}

package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.GameResultState
import blackjack.domain.Name
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.SuitType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 16점을 초과하면 카드를 받을 수 없다`() {
        val dealer = Dealer()
        val card1 = Card(SuitType.HEART, Denomination.TEN)

        dealer.hit(card1)
        assertThat(dealer.canHit()).isTrue

        val card2 = Card(SuitType.HEART, Denomination.SEVEN)
        dealer.hit(card2)
        assertThat(dealer.canHit()).isFalse
    }

    @Test
    fun `딜러는 플레이어들과의 점수를 비교한 GameResult를 만들 수 있다`() {
        val cards1 = Cards.from(
            listOf(
                Card(suitType = SuitType.HEART, denomination = Denomination.QUEEN),
                Card(suitType = SuitType.HEART, denomination = Denomination.SEVEN)
            )
        )
        val dealer = Dealer(cards = cards1)

        val cards2 = Cards.from(
            listOf(
                Card(suitType = SuitType.HEART, denomination = Denomination.TWO),
                Card(suitType = SuitType.HEART, denomination = Denomination.THREE)
            )
        )

        val cards3 = Cards.from(
            listOf(
                Card(suitType = SuitType.HEART, denomination = Denomination.TEN),
                Card(suitType = SuitType.HEART, denomination = Denomination.TEN)
            )
        )

        val players = Players.from(
            listOf(
                Player.of(name = Name.from("player1"), cards2),
                Player.of(name = Name.from("player2"), cards3)
            )
        )

        assertThat(dealer.makeDealerGameResult(players)).isEqualTo(
            GameResult.from(
                listOf(
                    GameResultState.Win,
                    GameResultState.Lose
                ).associateWith { 1 }
            )
        )
    }

    @Test
    fun `딜러가 플레이어보다 점수가 높으면 승리한다`() {
        val dealer = Dealer()
        dealer.hit(Card(SuitType.HEART, Denomination.TEN))
        dealer.hit(Card(SuitType.HEART, Denomination.TEN))

        val player = Player.of(name = Name.from("플레이어"))
        player.hit(Card(SuitType.HEART, Denomination.TEN))
        player.hit(Card(SuitType.HEART, Denomination.TWO))

        assertThat(dealer.result(player)).isEqualTo(GameResultState.Win)
    }

    @Test
    fun `딜러와 플레이어가 점수가 같으면 무승부이다`() {
        val dealer = Dealer()
        dealer.hit(Card(SuitType.HEART, Denomination.TEN))
        dealer.hit(Card(SuitType.HEART, Denomination.TEN))

        val player = Player.of(name = Name.from("플레이어"))
        player.hit(Card(SuitType.HEART, Denomination.TEN))
        player.hit(Card(SuitType.HEART, Denomination.TEN))

        assertThat(dealer.result(player)).isEqualTo(GameResultState.Draw)
    }

    @Test
    fun `딜러가 Bust면 다른 플레이어가 Bust라도 무조건 패배한다`() {
        val dealer = Dealer()
        dealer.hit(Card(SuitType.HEART, Denomination.TEN))
        dealer.hit(Card(SuitType.HEART, Denomination.SIX))
        dealer.hit(Card(SuitType.HEART, Denomination.TEN))

        val player = Player.of(name = Name.from("플레이어"))
        player.hit(Card(SuitType.HEART, Denomination.TEN))
        player.hit(Card(SuitType.HEART, Denomination.TEN))
        player.hit(Card(SuitType.HEART, Denomination.TEN))

        assertThat(dealer.result(player)).isEqualTo(GameResultState.Lose)
        assertThat(player.result(dealer)).isEqualTo(GameResultState.Win)
    }
}

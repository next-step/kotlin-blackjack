package blackjack

import blackjack.domain.Dealer
import blackjack.domain.GamePlayer
import blackjack.domain.Money
import blackjack.domain.Name
import blackjack.domain.PlayerBettings
import blackjack.domain.Players
import blackjack.domain.card.Cards
import blackjack.domain.state.Stay
import blackjack.state.CARD_HEART_EIGHT
import blackjack.state.CARD_HEART_FOUR
import blackjack.state.CARD_HEART_KING
import blackjack.state.CARD_HEART_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerBettingsTest {

    @Test
    fun `PlayerBettings를 Name, Money Map을 통해 만들 수 있다`() {
        val playerNames = listOf(
            Name.from("sh"),
            Name.from("seunghwan")
        )
        val moneys = listOf(
            Money.from("2000"),
            Money.from("1000")
        )

        val map: Map<Name, Money> = playerNames.zip(moneys).toMap()

        val playerBettings = PlayerBettings.from(map)

        assertThat(playerBettings.bettingsMap.keys.toList()).isEqualTo(playerNames)
        assertThat(playerBettings.bettingsMap.values.toList()).isEqualTo(moneys)
    }

    @Test
    fun `PlayerBettins를 통해 dealer의 총 profit을 구할 수 있다`() {
        val dealerCards = Cards(listOf(CARD_HEART_KING, CARD_HEART_EIGHT))
        val dealerState = Stay(dealerCards)
        val dealer = Dealer(state = dealerState)

        val gamePlayerCards1 = Cards(listOf(CARD_HEART_TWO, CARD_HEART_KING))
        val gamePlayerState1 = Stay(gamePlayerCards1)
        val gamePlayer1 = GamePlayer(name = Name.from("플레이어1"), gamePlayerState1)

        val gamePlayerCards2 = Cards(listOf(CARD_HEART_TWO, CARD_HEART_FOUR))
        val gamePlayerState2 = Stay(gamePlayerCards2)
        val gamePlayer2 = GamePlayer(name = Name.from("플레이어2"), gamePlayerState2)

        val players = Players.from(listOf(gamePlayer1, gamePlayer2))

        val playerNames = listOf(Name.from("플레이어1"), Name.from("플레이어2"))
        val moneys = listOf(Money.from("2000"), Money.from("1000"))
        val map: Map<Name, Money> = playerNames.zip(moneys).toMap()
        val playerBettings = PlayerBettings.from(map)

        val profit = playerBettings.calculateDealerProfit(dealer, players)

        assertThat(profit).isEqualTo(3000)
    }

    @Test
    fun `PlayerBettins를 통해 Player들의 Name, Profit으로 이루어진 Map을 구할 수 있다`() {
        val dealerCards = Cards(listOf(CARD_HEART_KING, CARD_HEART_EIGHT))
        val dealerState = Stay(dealerCards)
        val dealer = Dealer(state = dealerState)

        val gamePlayerCards1 = Cards(listOf(CARD_HEART_TWO, CARD_HEART_KING))
        val gamePlayerState1 = Stay(gamePlayerCards1)
        val gamePlayer1 = GamePlayer(name = Name.from("플레이어1"), gamePlayerState1)

        val gamePlayerCards2 = Cards(listOf(CARD_HEART_TWO, CARD_HEART_FOUR))
        val gamePlayerState2 = Stay(gamePlayerCards2)
        val gamePlayer2 = GamePlayer(name = Name.from("플레이어2"), gamePlayerState2)

        val players = Players.from(listOf(gamePlayer1, gamePlayer2))

        val playerNames = listOf(Name.from("플레이어1"), Name.from("플레이어2"))
        val moneys = listOf(Money.from("2000"), Money.from("1000"))
        val map: Map<Name, Money> = playerNames.zip(moneys).toMap()
        val playerBettings = PlayerBettings.from(map)

        val profitMap = playerBettings.calculatePlayersProfit(dealer, players)

        assertThat(profitMap.keys.toList()).isEqualTo(playerNames)
        assertThat(profitMap.values.toList()).isEqualTo(listOf(-2000, -1000))
    }
}

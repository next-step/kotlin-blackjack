package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player
import blackjack.dto.GeneratePlayerRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    private lateinit var blackJackGame: BlackJackGame

    @BeforeEach()
    fun setUp() {
        blackJackGame = BlackJackGame(NotRandomDeckShuffleStrategy())
    }

    @Test
    fun `게임이 시작되면 플레이어와 딜러에게 카드를 2장씩 나눠준다`() {
        // given
        val bettingMoney = 1
        val pobi = GeneratePlayerRequest("pobi", bettingMoney)
            .generatePlayer()
        val jason = GeneratePlayerRequest("jason", bettingMoney)
            .generatePlayer()
        val dealer = Dealer()

        val blackJackGamerList = listOf(
            pobi, jason, dealer
        )

        val pobiCards = listOf(
            Card(shape = CardShape.DIAMOND, number = CardNumber.J),
            Card(shape = CardShape.DIAMOND, number = CardNumber.Q)
        )
        val jasonCards = listOf(
            Card(shape = CardShape.DIAMOND, number = CardNumber.K),
            Card(shape = CardShape.DIAMOND, number = CardNumber.TEN)
        )

        val dealerCards = listOf(
            Card(shape = CardShape.DIAMOND, number = CardNumber.NINE),
            Card(shape = CardShape.DIAMOND, number = CardNumber.EIGHT)
        )

        // when
        blackJackGame.firstDraw(blackJackGamerList)

        // then
        Assertions.assertThat(pobi.getCards()).isEqualTo(pobiCards)
        Assertions.assertThat(jason.getCards()).isEqualTo(jasonCards)
        Assertions.assertThat(dealer.getCards()).isEqualTo(dealerCards)
    }

    @Test
    fun `플레이어가 카드 뽑는것을 선택하면 카드를 1장 나눠준다`() {
        // given
        val playerName = "pobi"
        val bettingMoney = 1
        val pobi = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        // when
        blackJackGame.oneGamerDraw(pobi)
        val pobiCards = listOf(Card(shape = CardShape.DIAMOND, number = CardNumber.J))

        // then
        Assertions.assertThat(pobi.getCards()).isEqualTo(pobiCards)
    }

    @Test
    fun `플레이어의 카드합이 21이 넘으면 false를 리턴한다`() {
        // given
        val playerName = "pobi"
        val bettingMoney = 1
        val pobi = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        // when
        val cards = listOf(
            Card(shape = CardShape.CLOVER, number = CardNumber.TEN),
            Card(shape = CardShape.CLOVER, number = CardNumber.TEN),
            Card(shape = CardShape.CLOVER, number = CardNumber.TWO)
        )
        pobi.addCards(cards)

        // then
        Assertions.assertThat(blackJackGame.checkBlackJackGamerIsDraw(pobi)).isEqualTo(false)
    }
}

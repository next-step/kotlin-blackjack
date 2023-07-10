package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.gamer.Dealer
import blackjack.dto.GeneratePlayerRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RuleCheckerTest {

    private lateinit var ruleChecker: RuleChecker

    @BeforeEach
    fun setUp() {
        ruleChecker = RuleChecker()
    }

    @ParameterizedTest
    @CsvSource(
        "FOUR,SIX,TEN,true",
        "FIVE,SIX,TEN,true",
        "SEVEN,SIX,TEN,false",
        delimiter = ','
    )
    fun `플레이어 타입일 때 카드 숫자 합을 21을 기준으로 검사 한다`(
        firstNumber: CardNumber,
        secondNumber: CardNumber,
        thirdNumber: CardNumber,
        answer: Boolean
    ) {
        // given
        val bettingMoney = 1
        val player = GeneratePlayerRequest("name", bettingMoney)
            .generatePlayer()

        player.addCards(
            listOf(
                Card(CardShape.CLOVER, firstNumber),
                Card(CardShape.CLOVER, secondNumber),
                Card(CardShape.CLOVER, thirdNumber)
            )
        )

        // when
        val actual = ruleChecker.checkSumOfCardNumbers(player)

        // then
        Assertions.assertThat(actual).isEqualTo(answer)
    }

    @ParameterizedTest
    @CsvSource(
        "TWO,THREE,TEN,true",
        "TWO,FOUR,TEN,true",
        "THREE,FOUR,TEN,false",
        delimiter = ','
    )
    fun `딜러 타입일 때 카드 숫자 합을 16을 기준으로 검사 한다(딜러는 카드를 뽑을때 16을 기준으로 체크한다)`(
        firstNumber: CardNumber,
        secondNumber: CardNumber,
        thirdNumber: CardNumber,
        answer: Boolean
    ) {
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, firstNumber),
                Card(CardShape.CLOVER, secondNumber),
                Card(CardShape.CLOVER, thirdNumber)
            )
        )

        val actual = ruleChecker.checkSumOfCardNumbers(dealer)

        Assertions.assertThat(actual).isEqualTo(answer)
    }

    @Test
    fun `플레이어의 카드 숫자의 합이 21이 넘으면 플레이어는 베팅 금액을 모두 잃고 딜러가 돈을 가져간다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN)
            )
        )

        val bettingMoney = 10000
        val player = GeneratePlayerRequest("name", bettingMoney)
            .generatePlayer()

        player.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TWO)
            )
        )

        // when
        ruleChecker.proceedWhoIsWinner(player, dealer)

        // then
        Assertions.assertThat(player.money).isEqualTo(-bettingMoney)
        Assertions.assertThat(dealer.money).isEqualTo(bettingMoney)
    }

    @Test
    fun `플레이어가 블랙잭이 아니고 이기면 베팅한 금액을 가져가고 딜러는 베팅한 금액만큼 잃는다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.THREE),
                Card(CardShape.CLOVER, CardNumber.TEN)
            )
        )

        val bettingMoney = 10000
        val player = GeneratePlayerRequest("name", bettingMoney)
            .generatePlayer()

        player.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN),
            )
        )

        // when
        ruleChecker.proceedWhoIsWinner(player, dealer)

        // then
        Assertions.assertThat(player.money).isEqualTo(bettingMoney)
        Assertions.assertThat(dealer.money).isEqualTo(-bettingMoney)
    }

    @Test
    fun `플레이어가 블랙잭으로 이기면 베팅 금액의 일점오배를 가져가고 딜러는 그 금액만큼 잃는다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN)
            )
        )

        val bettingMoney = 10000
        val player = GeneratePlayerRequest("name", bettingMoney)
            .generatePlayer()

        player.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.A)
            )
        )

        // when
        ruleChecker.proceedWhoIsWinner(player, dealer)

        // then
        Assertions.assertThat(player.money).isEqualTo(15000)
        Assertions.assertThat(dealer.money).isEqualTo(-15000)
    }

    @Test
    fun `딜러의 카드 숫자의 합이 21이 넘으면 플레이어는 베팅 금액을 가져간다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TWO)
            )
        )

        val bettingMoney = 10000
        val player = GeneratePlayerRequest("name", bettingMoney)
            .generatePlayer()

        player.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN),
            )
        )

        // when
        ruleChecker.proceedWhoIsWinner(player, dealer)

        // then
        Assertions.assertThat(player.money).isEqualTo(bettingMoney)
        Assertions.assertThat(dealer.money).isEqualTo(-bettingMoney)
    }

    @Test
    fun `딜러와 플레이어가 비기는 경우 플레이어는 베팅한 금액을 돌려받는다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN)
            )
        )

        val bettingMoney = 10000
        val player = GeneratePlayerRequest("name", bettingMoney)
            .generatePlayer()

        player.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN)
            )
        )

        // when
        ruleChecker.proceedWhoIsWinner(player, dealer)

        // then
        Assertions.assertThat(player.money).isEqualTo(bettingMoney)
        Assertions.assertThat(dealer.money).isEqualTo(0)
    }
}

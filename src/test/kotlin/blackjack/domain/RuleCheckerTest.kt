package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.GameRecordType
import blackjack.domain.gamer.Player
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
        val player = Player("name")
        player.addCards(
            listOf(
                Card(CardShape.CLOVER, firstNumber),
                Card(CardShape.CLOVER, secondNumber),
                Card(CardShape.CLOVER, thirdNumber)
            )
        )

        val actual = ruleChecker.checkSumOfCardNumbers(player)

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
    fun `딜러의 카드가 21을 넘어가면 플레이어의 카드와 상관없이 플레이어가 이긴다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TWO)
            )
        )
        val player = Player("playerName")
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
        Assertions.assertThat(dealer.getTotalGameRecord()).isEqualTo(
            listOf(
                (GameRecordType.WIN to 0),
                (GameRecordType.LOSE to 1),
                (GameRecordType.DRAW to 0)
            )
        )
        Assertions.assertThat(player.getGameRecord()).isEqualTo(GameRecordType.WIN)
    }

    @Test
    fun `딜러의 카드가 21보다 작을 때 플레이어의 카드가 21을 넘어가면 딜러가 이긴다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN)
            )
        )
        val player = Player("playerName")
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
        Assertions.assertThat(dealer.getTotalGameRecord()).isEqualTo(
            listOf(
                (GameRecordType.WIN to 1),
                (GameRecordType.LOSE to 0),
                (GameRecordType.DRAW to 0)
            )
        )
        Assertions.assertThat(player.getGameRecord()).isEqualTo(GameRecordType.LOSE)
    }

    @Test
    fun `딜러와 플레이어의 카드 숫자합이 21 이하이고 같으면 비긴다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.A)
            )
        )
        val player = Player("playerName")
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
        Assertions.assertThat(dealer.getTotalGameRecord()).isEqualTo(
            listOf(
                (GameRecordType.WIN to 0),
                (GameRecordType.LOSE to 0),
                (GameRecordType.DRAW to 1)
            )
        )
        Assertions.assertThat(player.getGameRecord()).isEqualTo(GameRecordType.DRAW)
    }

    @Test
    fun `딜러와 플레이어의 카드가 둘다 21보다 작을 경우 값을 비교해서 승자를 가린다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TEN)
            )
        )
        val player = Player("playerName")
        player.addCards(
            listOf(
                Card(CardShape.CLOVER, CardNumber.TEN),
                Card(CardShape.CLOVER, CardNumber.TWO)
            )
        )
        // when
        ruleChecker.proceedWhoIsWinner(player, dealer)

        // then
        Assertions.assertThat(dealer.getTotalGameRecord()).isEqualTo(
            listOf(
                (GameRecordType.WIN to 1),
                (GameRecordType.LOSE to 0),
                (GameRecordType.DRAW to 0)
            )
        )
        Assertions.assertThat(player.getGameRecord()).isEqualTo(GameRecordType.LOSE)
    }
}

package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
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
    fun `딜러 타입일 때 카드 숫자 합을 16을 기준으로 검사 한다`(
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
}

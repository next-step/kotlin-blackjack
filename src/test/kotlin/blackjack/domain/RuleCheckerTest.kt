package blackjack.domain

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

//    @ParameterizedTest
//    @CsvSource(
//        "10,true",
//        "21,true",
//        "22,false",
//        delimiter = ','
//    )
//    fun `카드 숫자합이 21이 넘는지 검사한다`(sum: Int, answer: Boolean) {
//        val actual = ruleChecker.checkSumOfCardNumbers(sum)
//
//        Assertions.assertThat(actual).isEqualTo(answer)
//    }
}

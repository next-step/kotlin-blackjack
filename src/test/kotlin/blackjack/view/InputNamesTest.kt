package blackjack.view

import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class InputNamesTest {

    @DisplayName("쉼표를 구분자로 이름이 입력되면 이름 리스트를 반환한다")
    @Test
    fun nameList() {
        val inputs = listOf(
            "name1,name2" to listOf("name1", "name2"),
            "name1,name2,name3" to listOf("name1", "name2", "name3")
        )

        inputs.forAll { input ->
            InputNames(input.first).parseNames() shouldBe input.second
        }
    }
}

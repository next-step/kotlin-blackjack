package blackjack_dealer

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData

class InputValueTest : FunSpec({
    context("잘못된 값을 입력했을 때 IllegalArgumentException을 발생시킨다.") {
        val list = listOf(
            "pita, haero,", // 쉼표 뒤에 아무런 이름이 없을 때
            ",pita,haero",  // 쉼표가 맨 앞에 있을 때
            "",             // 아무런 값이 입력 되지 않았을 때
        )
        shouldThrow<IllegalArgumentException> {
            withData(list) { input ->
                
            }
        }
    }
})

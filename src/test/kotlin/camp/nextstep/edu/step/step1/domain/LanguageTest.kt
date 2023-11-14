package camp.nextstep.edu.step.step1.domain

import camp.nextstep.edu.step.step1.builder.LanguageBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName

@DisplayName("언어는")
class LanguageTest : BehaviorSpec({

    Given("사용 가능한 언어들이 주어지고") {
        val requestLanguages = listOf("Korean", "English")

        When("언어 도메인을 생성시") {
            val languages = introduceLanguage {
                requestLanguages[0].level(5)
                requestLanguages[1].level(3)
            }

            Then("언어 도메인이 생성된다") {
                languages[0].name shouldBe "Korean"
                languages[0].level shouldBe 5
                languages[1].name shouldBe "English"
                languages[1].level shouldBe 3
            }
        }
    }

    Given("만약, 빈 문자열또는 1~5 사이가 아닌 레벨이 주어지고") {
        val blankLanguage = ""
        val largeLevel = 6

        When("생성을 요청하면") {
            val exceptionByBlankLanguageName = shouldThrow<IllegalArgumentException> {
                introduceLanguage {
                    blankLanguage.level(5)
                }
            }

            val exceptionByLargeLevel = shouldThrow<IllegalArgumentException> {
                introduceLanguage {
                    "Korea".level(largeLevel)
                }
            }

            Then("예외가 발생한다.") {
                exceptionByBlankLanguageName.message shouldBe "언어 이름이 입력되지 않았습니다."
                exceptionByLargeLevel.message shouldBe "언어 레벨은 1~5 사이의 값이어야 합니다."
            }
        }
    }

})

fun introduceLanguage(block: LanguageBuilder.() -> Unit): List<Language> {
    return LanguageBuilder().apply(block).build()
}

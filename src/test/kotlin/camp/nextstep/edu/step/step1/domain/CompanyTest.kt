package camp.nextstep.edu.step.step1.domain

import camp.nextstep.edu.step.step1.builder.CompanyBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CompanyTest : BehaviorSpec({

    Given("회사 이름이 주어지면") {
        val companyName = "NEXTSTEP"

        When("회사를 생성하면") {
            val company = introduceCompany {
                name(companyName)
            }

            Then("요청한 이름의 회사가 생성된다") {
                company.name shouldBe "NEXTSTEP"
            }
        }
    }

    Given("만약 빈 회사이름이 주어지고") {
        val blankCompanyName = ""

        When("회사를 생성하면") {
            val exceptionByBlankCompanyName = shouldThrow<IllegalArgumentException> {
                introduceCompany {
                    name(blankCompanyName)
                }
            }

            Then("예외가 발생한다.") {
                exceptionByBlankCompanyName.message shouldBe "회사 이름이 입력되지 않았습니다."
            }
        }
    }

})

fun introduceCompany(block: CompanyBuilder.() -> Unit): Company {
    return CompanyBuilder().apply(block).build()
}

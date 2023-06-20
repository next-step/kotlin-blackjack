package dsl

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DslTest : FreeSpec({

    "introduce 생성" - {
        withData(
            "제이든",
            "김진억"
        ) { value ->
            val person = introduce {
                name(value)
            }
            person.name shouldBe value
        }
    }

    "company 등록" - {
        withData(
            "카카오",
            "넥스트스탭"
        ) { companyName ->
            val name = "김진억"
            val person = introduce {
                name(name)
                company(companyName)
            }

            person.name shouldBe name
            person.company shouldBe companyName
        }
    }
})

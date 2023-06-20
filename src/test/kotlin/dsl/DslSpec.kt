package dsl

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class DslSpec : DescribeSpec({
    describe("(DSL 테스트) Person 객체 생성") {
        withData(
            nameFn = { value -> "이름이 $value 인 객체를 생성할 수 있다." },
            ts = listOf("이현재", "홍길동"),
        ) { value ->
            val person: Person = introduce {
                name(value)
            }

            person.name shouldBe value
            person.company shouldBe null
            person.skills.softs.isEmpty() shouldBe true
            person.skills.hards.isEmpty() shouldBe true
            person.languages.values.isEmpty() shouldBe true
        }

        context("DSL 을 통해 Person 객체를 생성하면") {
            val person = introduce {
                name("이현재")
                company("우아한형제들")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }

            it("이름이 설정된다.") {
                person.name shouldBe "이현재"
            }

            it("회사가 설정된다.") {
                person.company shouldBe "우아한형제들"
            }

            it("스킬이 설정된다.") {
                person.skills shouldNotBe null
                person.skills.softs.size shouldBe 2
                person.skills.hards.size shouldBe 1
                person.skills.softs[0] shouldBe "A passion for problem solving"
                person.skills.softs[1] shouldBe "Good communication skills"
                person.skills.hards[0] shouldBe "Kotlin"
            }

            it("언어가 설정된다.") {
                person.languages!!
                person.languages.size shouldBe 2
                person.languages.values[0].name shouldBe "Korean"
                person.languages.values[0].level shouldBe 5
                person.languages.values[1].name shouldBe "English"
                person.languages.values[1].level shouldBe 3
            }
        }
    }
})

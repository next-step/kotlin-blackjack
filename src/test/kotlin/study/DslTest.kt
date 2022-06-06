package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class DslTest : FunSpec({
    test("introduce") {
        val person: Person = introduce {
            name("홍길동")
            company("맘편한세상")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
                hard("Java")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "맘편한세상"
        person.skills.softSkills.values shouldContainExactly listOf("A passion for problem solving", "Good communication skills")
        person.skills.hardSkills.values shouldContainExactly listOf("Kotlin", "Java")
        person.languages.values shouldContainExactly listOf(Language("Korean", 5), Language("English", 3))
    }
})

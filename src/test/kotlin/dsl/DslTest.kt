package dsl

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person = introduce {
            name("전성모")
        }
        person.name shouldBe "전성모"
    }

    @Test
    fun company() {
        val person = introduce {
            name("전성모")
            company("회사")
        }
        person.name shouldBe "전성모"
        person.company shouldBe "회사"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("전성모")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "전성모"
        person.company shouldBe "회사"
        person.skills?.soft?.shouldContain("A passion for problem solving")
        person.skills?.soft?.shouldContain("Good communication skills")
        person.skills?.hard?.shouldContain("Kotlin")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("전성모")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.name shouldBe "전성모"
        person.languages?.values?.shouldContain(Language("Korean", 5))
        person.languages?.values?.shouldContain(Language("English", 3))
    }

    @Test
    fun all() {
        val person = introduce {
            name("전성모")
            company("회사")
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
        person.name shouldBe "전성모"
        person.company shouldBe "회사"
        person.skills?.soft?.shouldContain("A passion for problem solving")
        person.skills?.soft?.shouldContain("Good communication skills")
        person.skills?.hard?.shouldContain("Kotlin")
        person.languages?.values?.shouldContain(Language("Korean", 5))
        person.languages?.values?.shouldContain(Language("English", 3))
    }
}

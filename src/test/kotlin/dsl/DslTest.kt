package dsl

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
    name("최원준")
    company("메쉬코리아")
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
*/
class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("최원준")
            company("메쉬코리아")
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
        person.name shouldBe "최원준"
        person.company shouldBe "메쉬코리아"

        person.skills.values shouldContainExactly listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )

        person.languages.values shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }

    @Test
    fun name() {
        val person: Person = introduce {
            name("최원준")
        }

        person.name shouldBe "최원준"
        person.company.shouldBeNull()
        person.skills shouldBe Skills()
        person.languages shouldBe Languages()
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("최원준")
            company("메쉬코리아")
        }

        person.name shouldBe "최원준"
        person.company shouldBe "메쉬코리아"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("최원준")
            company("메쉬코리아")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "최원준"
        person.company shouldBe "메쉬코리아"

        person.skills.values shouldContainExactly listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("최원준")
            company("메쉬코리아")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.name shouldBe "최원준"
        person.company shouldBe "메쉬코리아"

        person.languages.values shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

package kotlinDSL

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("박재성")
 *   company("우아한형제들")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 3
 *   }
 * }
 */

class DslTest {
    @ValueSource(strings = ["홍길동", "김태훈"])
    @ParameterizedTest
    fun name(value: String) {
        val person: Person =
            introduce {
                name(value)
            }
        person.name shouldBe value
    }

    @Test
    fun company() {
        val person: Person =
            introduce {
                name("김태훈")
                company("컴파니")
            }

        person.name shouldBe "김태훈"
        person.company shouldBe "컴파니"
    }

    @Test
    fun skills() {
        val person: Person =
            introduce {
                name("김태훈")
                company("컴파니")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        val soft: MutableList<String> = mutableListOf()
        soft.add("A passion for problem solving")
        soft.add("Good communication skills")

        val hard: MutableList<String> = mutableListOf()
        hard.add("Kotlin")

        val skills = Skills(soft, hard)

        person.name shouldBe "김태훈"
        person.company shouldBe "컴파니"
        person.skills shouldBe skills
    }

    @Test
    fun languages() {
        val person: Person =
            introduce {
                name("김태훈")
                company("컴파니")
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }

        val languages: MutableList<Language> = mutableListOf()
        languages.add(Language("Korean", 5))
        languages.add(Language("English", 3))

        person.name shouldBe "김태훈"
        person.company shouldBe "컴파니"
        person.languages shouldBe Languages(languages)
    }

    @Test
    fun introduce() {
        val person: Person =
            introduce {
                name("김태훈")
                company("컴파니")
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

        val soft: MutableList<String> = mutableListOf()
        soft.add("A passion for problem solving")
        soft.add("Good communication skills")

        val hard: MutableList<String> = mutableListOf()
        hard.add("Kotlin")

        val skills = Skills(soft, hard)

        val languages: MutableList<Language> = mutableListOf()
        languages.add(Language("Korean", 5))
        languages.add(Language("English", 3))

        person.name shouldBe "김태훈"
        person.company shouldBe "컴파니"
        person.skills shouldBe skills
        person.languages shouldBe Languages(languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
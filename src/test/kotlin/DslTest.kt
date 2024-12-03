import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *     name("신진혁")
 *     company("맵시")
 *     skills {
 *         soft("A passion for problem solving")
 *         soft("Good communication skills")
 *         hard("Kotlin")
 *     }
 *     languages {
 *         "Korean" level 5
 *         "English" level 3
 *     }
 * }
 */
class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "홍"])
    fun nameTest(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun companyTest() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skillsTest() {
        val person = introduce {
            name("ㅇㅇ")
            company("dd")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills shouldBe listOf("A passion for problem solving", "Good communication skills", "Kotlin")
    }

    @Test
    fun languagesTest() {
        val person = introduce {
            name("ㅇㅇ")
            company("dd")
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

        person.languages shouldBe listOf(
            Person.Language("Korean", 5),
            Person.Language("English", 3)
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person =
    PersonBuilder()
        .apply(block)
        .build()

data class Person(
    val name: String,
    val company: String?,
    val skills: List<String>,
    val languages: List<Language>,
) {
    data class Language(
        val language: String,
        val level: Int,
    )
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills = mutableListOf<String>()
    private var languages = mutableListOf<Person.Language>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: () -> Unit) {
        block()
    }

    fun soft(value: String) {
        skills.add(value)
    }

    fun hard(value: String) {
        skills.add(value)
    }

    fun languages(block: () -> Unit) {
        block()
    }

    infix fun String.level(num: Int) {
        languages.add(Person.Language(this, num))
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
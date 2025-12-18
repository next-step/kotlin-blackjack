package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.collections.mutableListOf

class DslTest {
    @ValueSource(strings = ["손성현|이현규", "제이슨"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
            company("우아한형제들")

        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("손성현|이현규")
            company("우아한형제들")
        }
        assertThat(person.name).isEqualTo("손성현|이현규")
        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("손성현|이현규")
            company("우아한형제들")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.name).isEqualTo("손성현|이현규")
        assertThat(person.company).isEqualTo("우아한형제들")

        // skills 검증 : 갯수와 내용
        assertThat(person.skills).hasSize(3)
        assertThat(person.skills).extracting("description").containsExactlyInAnyOrder(
            "A passion for problem solving",
            "Good communication skills",
            "Kotlin"
        )
    }

    @Test
    fun language() {
        val person = introduce {
            name("손성현|이현규")
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

        assertThat(person.name).isEqualTo("손성현|이현규")
        assertThat(person.company).isEqualTo("우아한형제들")

        // skills 검증 : 갯수와 내용
        assertThat(person.skills).hasSize(3)
        assertThat(person.skills).extracting("description").containsExactlyInAnyOrder(
            "A passion for problem solving",
            "Good communication skills",
            "Kotlin"
        )

        // languages 검증 : 갯수와 내용
        assertThat(person.languages).hasSize(2)
        assertThat(person.languages).extracting("name").containsExactlyInAnyOrder(
            "Korean",
            "English"
        )
        assertThat(person.languages).extracting("level").containsExactlyInAnyOrder(
            5,
            3
        )
    }
}

fun introduce(block: @PersonDsl PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

@PersonDsl
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill> = emptyList()
    private var languages: List<Language> = emptyList()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: @PersonDsl SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: @PersonDsl LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

@PersonDsl
class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Skill(SkillType.SOFT, value))
    }

    fun hard(value: String) {
        skills.add(Skill(SkillType.HARD, value))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}

@PersonDsl
class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): List<Language> {
        return languages.toList()
    }
}

/**
 * Dsl Marker 정의
 * - @DslMarker 어노테이션을 사용하여 DSL 마커를 정의
 * - DSL 내부에서 중첩된 수신 객체 간의 혼동을 방지
 * - DSL 마커를 적용한 클래스나 함수는 동일한 DSL 컨텍스트 내에서만 사용 가능
 * - 이를 통해 가독성과 유지보수성을 향상
 */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class PersonDsl

// person 관련
data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList()
)

// skill 관련
data class Skill(val type: SkillType, val description: String)
enum class SkillType {
    SOFT,
    HARD,
}

// language 관련
data class Language(val name: String, val level: Int)


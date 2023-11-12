import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
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
    @ValueSource(strings = ["홍길동", "dokdogalmaegi"])
    @ParameterizedTest
    fun name(name: String) {
        val person: Person = introduce {
            name(name)
        }

        person.name shouldBe name
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("홍길동")
            company("활빈당")
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @ValueSource(strings = ["Kotlin", "Go", "Java"])
    @ParameterizedTest
    fun hard(skill: String) {
        val person: Person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                hard(skill)
            }
        }

        person.skills shouldContainExactly listOf(Skill(SkillType.Hard, skill))
    }

    @ValueSource(strings = ["A passion for problem solving", "Good communication skills", "Good voice"])
    @ParameterizedTest
    fun soft(skill: String) {
        val person: Person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft(skill)
            }
        }

        person.skills shouldContainExactly listOf(Skill(SkillType.Soft, skill))
    }

    @Test
    fun hardAndSoft() {
        val person: Person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills shouldContainExactly listOf(
            Skill(SkillType.Soft, "A passion for problem solving"),
            Skill(SkillType.Soft, "Good communication skills"),
            Skill(SkillType.Hard, "Kotlin"),
        )
    }

    @Test
    fun pair() {
        val pair1 = Pair(1, "one")
        val pair2 = 1 to "one"
        val pair3 = 1 of "one"
    }
}

infix fun Int.of(s: String): Pair<Int, String> = Pair(this, s)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(val name: String, val company: String?, val skills: List<Skill>?)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill>? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skills = SkillBuilder().apply(block).build()
    }

    fun build(): Person = Person(this.name, this.company, skills)
}

enum class SkillType {
    Hard,
    Soft
}

data class Skill(val skill: SkillType, val content: String)

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun hard(content: String) = setSkills(SkillType.Hard, content)

    fun soft(content: String) = setSkills(SkillType.Soft, content)

    private fun setSkills(skill: SkillType, content: String) {
        skills.add(Skill(skill, content))
    }

    fun build(): List<Skill> = this.skills
}

package study

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
    @ValueSource(strings = ["홍길동", "Albert"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills.size shouldBe 3
        person.skills[0].skillType shouldBe SkillType.SOFT
        person.skills[0].message shouldBe "A passion for problem solving"
        person.skills[1].skillType shouldBe SkillType.SOFT
        person.skills[1].message shouldBe "Good communication skills"
        person.skills[2].skillType shouldBe SkillType.HARD
        person.skills[2].message shouldBe "Kotlin"
    }

    @Test
    fun pair() {
        val pair1 = Pair(1, "one")
        val pair2 = 1 to "one"
        val pair3 = 1 of "one"
    }
}

infix fun Int.of(value: String): Pair<Int, String> = Pair(this, value)

fun introduce(block: Person.() -> Unit): Person = Person().apply(block)

class Person {
    lateinit var name: String
    lateinit var company: String
    lateinit var skills: List<Skill>

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) = SkillsBuilder().apply(block).build().let { skills = it }
}

class SkillsBuilder {
    private val skillsList = mutableListOf<Skill>()

    fun soft(value: String) {
        skillsList.add(Skill().apply {
            skillType = SkillType.SOFT
            message = value
        })
    }

    fun hard(value: String) {
        skillsList.add(Skill().apply {
            skillType = SkillType.HARD
            message = value
        })
    }

    fun build(): List<Skill> = skillsList
}

class Skill {
    lateinit var skillType: SkillType
    lateinit var message: String
}

enum class SkillType {
    SOFT, HARD;

    private lateinit var value: String

    fun value(value: String): SkillType {
        this.value = value
        return this
    }
}

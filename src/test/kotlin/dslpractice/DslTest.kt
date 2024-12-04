package dslpractice

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["게이츠", "잡스"])
    @ParameterizedTest
    fun name(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("게이츠")
                company("MS")
            }
        person.company shouldBe "MS"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("게이츠")
                company("MS")
                skills {
                    skill(SkillType.SOFT, "A passion for problem solving")
                    skill(SkillType.SOFT, "Good communication skills")
                    skill(SkillType.HARD, "Kotlin")
                }
            }
        person.skills.getSkills(SkillType.SOFT) shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills.getSkills(SkillType.HARD) shouldBe listOf("Kotlin")
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(val name: String, val company: String?, val skills: Skills)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(value: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(value).build()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}

data class Skills(
    private val skills: Map<SkillType, List<String>> = emptyMap(),
) {
    fun getSkills(type: SkillType): List<String> {
        return skills[type].orEmpty()
    }
}

class SkillsBuilder {
    private val skillsMap = mutableMapOf<SkillType, MutableList<String>>()

    fun skill(
        type: SkillType,
        description: String,
    ) {
        skillsMap.computeIfAbsent(type) { mutableListOf() }.add(description)
    }

    fun build(): Skills {
        return Skills(skillsMap.mapValues { it.value.toList() })
    }
}

enum class SkillType {
    SOFT,
    HARD,
}

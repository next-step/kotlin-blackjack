package study

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ValueSource(strings = ["이범석", "석범이"])
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
            name("이범석")
            company("deepmedi")
        }
        person.name shouldBe "이범석"
        person.company shouldBe "deepmedi"
    }

    @Test
    fun skillLanguage() {
        val person = introduce {
            name("이범석")
            company("deepmedi")
            skills {
                soft("A paasion for problem solving")
                soft("Good communication skills")
                hard("kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        with(person) {
            name shouldBe "이범석"
            company shouldBe "deepmedi"
            skills shouldNotBe null
            skills?.soft?.run {
                get(0) shouldBe Skill("A paasion for problem solving")
                get(1) shouldBe Skill("Good communication skills")
            }
            languages shouldNotBe null
            languages.run {
                isContains("Korean") shouldBe true
                obtainByName("Korean") shouldBe Language(name = "Korean", level = 5)
                isContains("English") shouldBe true
                obtainByName("English") shouldBe Language(name = "English", level = 3)
            }
        }
    }

    @Test
    fun `이름은 필수`() {
        shouldThrow<IllegalArgumentException> {
            introduce {
                company("deepmedi")
                skills {
                    soft("A paasion for problem solving")
                    soft("Good communication skills")
                    hard("kotlin")
                }
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        }
    }

    @Test
    fun `언어가 없으면 문맹`() {
        val person = introduce {
            name("이범석")
            company("deepmedi")
            skills {
                soft("A paasion for problem solving")
                soft("Good communication skills")
                hard("kotlin")
            }
        }
        person.isIlliterate() shouldBe true
    }

    @Test
    fun `회사와 스킬은 필수가 아니다`() {
        shouldNotThrow<Exception> {
            introduce {
                name("이범석")
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        }
    }
}

fun introduce(block: Person.PersonBuilder.() -> Unit): Person {
    return Person.PersonBuilder().apply(block).build()
}

class SkillBuilder {
    private var soft: List<Skill> = emptyList()
    private var hard: List<Skill> = emptyList()

    fun soft(value: String) {
        soft += Skill(value)
    }

    fun hard(value: String) {
        hard += Skill(value)
    }

    fun build(): Skills = Skills(soft = soft.toList(), hard = hard.toList())
}

class LanguagesBuilder(
    private var languages: List<Language> = emptyList(),
) {
    infix fun String.level(value: Int) {
        languages += Language(name = this@level, level = value)
    }

    fun build(): Languages = Languages(languages.toList())
}

class Person private constructor(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages,
) {
    fun isIlliterate(): Boolean = languages.isEmpty()

    class PersonBuilder {
        private var name: String? = null
        private var company: String? = null
        private var skills: Skills? = null
        private var languages: Languages = Languages()

        fun name(value: String) {
            name = value
        }

        fun company(value: String) {
            company = value
        }

        fun skills(block: SkillBuilder.() -> Unit) {
            skills = SkillBuilder().apply(block).build()
        }

        fun languages(block: LanguagesBuilder.() -> Unit) {
            languages = LanguagesBuilder().apply(block).build()
        }

        fun build(): Person {
            val buildName = name
            require(buildName != null) { "이름은 필수입니다." }

            return Person(
                name = buildName,
                company = company,
                skills = skills,
                languages = languages,
            )
        }
    }
}

data class Skills(val soft: List<Skill>, val hard: List<Skill>)

data class Skill(val skillName: String)

data class Languages(private val languages: List<Language> = emptyList()) {
    fun isEmpty(): Boolean = languages.isEmpty()

    fun isContains(language: String): Boolean {
        languages.forEach {
            if (it.name == language) return true
        }
        return false
    }

    fun obtainByName(name: String): Language? {
        return languages.find { it.name == name }
    }
}

data class Language(val name: String, val level: Int)

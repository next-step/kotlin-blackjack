package study

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

//introduce {
//    name("홍길동")
//    company("회사")
//    skills {
//        soft("A passion for problem solving")
//        soft("Good communication skills")
//        hard("Kotlin")
//    }
//    languages {
//        "Korean" level 5
//        "English" level 3
//    }
//}
class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("홍길동")
        }
        person.name shouldBe "홍길동"
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person: Person = introduce {
            company("회사")
        }
        person.company shouldBe "회사"
    }

    @Test
    fun finalIntroduce() {
        val person: Person = introduce {
            name("홍길동")
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
        person.name shouldBe "홍길동"
        person.company shouldBe "회사"
        person.skills shouldContainAll listOf(
            Skill(SkillType.SOFT, "A passion for problem solving"),
            Skill(SkillType.SOFT, "Good communication skills"),
            Skill(SkillType.HARD, "Kotlin")
        )
        person.languages shouldContainAll listOf(
            Language("Korean", 5), Language("English", 3)
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: List<Skill>
    private lateinit var languages: List<Language>
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }


    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }


}


class SkillBuilder {
    private var skills: MutableList<Skill> = mutableListOf()

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

class LanguageBuilder {
    private  var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages.toList()
    }


}

enum class SkillType {
    SOFT, HARD
}

data class Skill(val type: SkillType, val text: String)

data class Language(val languages: String, val level: Int)

data class Person(val name: String, val company: String?, val skills: List<Skill>, val languages: List<Language>)
